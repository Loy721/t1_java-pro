import annotation.AfterSuite;
import annotation.BeforeSuite;
import handler.TestAnnotationHandler;
import model.TestReport;
import util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestRunner {
    private final Class<?> clazz;
    private final Object instanceClazz;

    private final TestAnnotationHandler testAnnotationHandler = new TestAnnotationHandler();


    public TestRunner(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.clazz = clazz;
        this.instanceClazz = clazz.getDeclaredConstructor().newInstance();
    }

    private TestReport execute() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = clazz.getMethods();

        invokeStaticMethod(ReflectionUtil.getMethodWithSingleAnnotation(BeforeSuite.class, methods));

        TestReport result = testAnnotationHandler.handle(instanceClazz);

        invokeStaticMethod(ReflectionUtil.getMethodWithSingleAnnotation(AfterSuite.class, methods));

        return result;
    }

    public static TestReport runTest(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (new TestRunner(clazz)).execute();
    }

    private void invokeStaticMethod(Method method) throws InvocationTargetException, IllegalAccessException {
        if (method != null) {
            if (Modifier.isStatic(method.getModifiers())) {
                method.invoke(null);
            } else {
                throw new RuntimeException("Method " + method.getName() + " must be static");
            }
        }
    }
}
