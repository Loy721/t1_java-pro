package handler;

import annotation.CsvSource;
import annotation.Test;
import model.TestReport;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class TestAnnotationHandler {

    private final CsvSourceAnnotationHandler csvSourceAnnotationHandler = new CsvSourceAnnotationHandler();

    public TestReport handle(List<Method> beforeTestMethods, List<Method> testMethods, List<Method> afterTestMethods,
                             Object target) throws InvocationTargetException, IllegalAccessException {
        TestReport result = new TestReport(testMethods.size());
        for (Method method : validateAndSortTestAnnotationMethods(testMethods)) {
            for (Method methodBefore : beforeTestMethods) {
                methodBefore.invoke(target);
            }
            try {
                invokeTestTargetMethod(target, method);
            } catch (Exception e) {
                e.printStackTrace();
                result.incFailureTest();
            }
            for (Method methodAfter : afterTestMethods) {
                methodAfter.invoke(target);
            }
        }
        return result;
    }

    private List<Method> validateAndSortTestAnnotationMethods(List<Method> methods) {
        boolean hasInvalidPriority = methods.stream().map(x -> x.getAnnotation(Test.class))
                .anyMatch(annotation -> annotation.priority() < 1 || annotation.priority() > 10);
        if (hasInvalidPriority) {
            throw new IllegalArgumentException("Some methods has invalid value priority");
        }
        return methods.stream().filter(x -> x.isAnnotationPresent(Test.class))
                .sorted(comparingInt(x -> x.getAnnotation(Test.class).priority())).toList();
    }

    private void invokeTestTargetMethod(Object instance, Method method) throws InvocationTargetException, IllegalAccessException {
        if (method.isAnnotationPresent(CsvSource.class)) {
            Object[] args = csvSourceAnnotationHandler.handle(method);
            method.invoke(instance, args);
            return;
        }
        method.invoke(instance);
    }
}
