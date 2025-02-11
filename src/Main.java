public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(4);
        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000 * finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task: " + finalI);
            });
        }
        boolean b1 = threadPool.awaitTermination(4000);
        System.out.println("Is await termination after 4 seconds returned value: " + b1);
        threadPool.shutdown();
        try {
            threadPool.execute(() -> System.out.println("Try add task when thread pool is shutdown"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //последняя задача выполняется приблизительно 6 секунд
        boolean b2 = threadPool.awaitTermination(3000);
        System.out.println("Is await termination after 7 seconds and shutdown returned value: " + b2);
    }
}