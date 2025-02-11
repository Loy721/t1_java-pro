import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {
    private final List<TaskRunner> taskRunners = new ArrayList<>();
    private final Queue<Runnable> tasks = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean isShutdown = false;

    public ThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            TaskRunner taskRunner = new TaskRunner();
            taskRunners.add(taskRunner);
            taskRunner.start();
        }
    }


    public void execute(Runnable task) {
        lock.lock();
        try {
            if (isShutdown) {
                throw new IllegalStateException("ThreadPool is shutdown and can not accept new tasks");
            }
            tasks.add(task);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            isShutdown = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean awaitTermination(long millis) {
        long endTime = System.currentTimeMillis() + millis;
        while (System.currentTimeMillis() < endTime) {
            if (taskRunners.stream().allMatch(tr -> tr.getState().equals(Thread.State.TERMINATED))) {
                return true;
            }
        }
        return false;
    }

    private class TaskRunner extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task;
                lock.lock();
                try {
                    while (tasks.isEmpty()) {
                        if (isShutdown) {
                            return;
                        }
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    task = tasks.poll();
                } finally {
                    lock.unlock();
                }
                try {
                    task.run();
                } catch (Exception e) {
                    System.err.println("Task error: " + e.getMessage());
                }
            }
        }
    }
}
