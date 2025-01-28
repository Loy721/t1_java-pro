package handler;

import annotation.AfterTest;
import annotation.BeforeTest;
import annotation.CsvSource;
import annotation.Test;
import model.TestReport;
import util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class TestAnnotationHandler {

    private final CsvSourceAnnotationHandler csvSourceAnnotationHandler = new CsvSourceAnnotationHandler();

    public TestReport handle(Object target) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = target.getClass().getMethods();
        Method beforeEachTestMethod = ReflectionUtil.getMethodWithSingleAnnotation(BeforeTest.class, methods);
        Method afterEachTestMethod = ReflectionUtil.getMethodWithSingleAnnotation(AfterTest.class, methods);
        List<Method> testMethods = getTestMethods(methods);
        TestReport result = new TestReport(testMethods.size());

        for (Method method : testMethods) {
            if (beforeEachTestMethod != null) {
                beforeEachTestMethod.invoke(target);
            }
            try {
                invokeTestTargetMethod(target, method);
            } catch (Exception e) {
                e.printStackTrace();
                result.incFailureTest();
            }
            if (afterEachTestMethod != null) {
                afterEachTestMethod.invoke(target);
            }
        }
        return result;
    }

    private void invokeTestTargetMethod(Object instance, Method method) throws InvocationTargetException, IllegalAccessException {
        if (method.isAnnotationPresent(CsvSource.class)) {
            Object[] args = csvSourceAnnotationHandler.handle(method);
            method.invoke(instance, args);
            return;
        }
        method.invoke(instance);
    }

    private List<Method> getTestMethods(Method[] declaredMethods) {
        List<Method> list = Arrays.stream(declaredMethods).filter(x -> x.isAnnotationPresent(Test.class))
                .sorted(comparingInt(x -> x.getAnnotation(Test.class).priority())).toList();
        boolean hasInvalidPriority = list.stream().map(x -> x.getAnnotation(Test.class))
                .anyMatch(annotation -> annotation.priority() < 1 || annotation.priority() > 10);
        if (hasInvalidPriority) {
            throw new IllegalArgumentException("Some methods has invalid value priority");
        }
        return list;
    }
}
