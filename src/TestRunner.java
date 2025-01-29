import annotation.*;
import handler.TestAnnotationHandler;
import model.TestReport;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestRunner {
    private static Map<Class<? extends Annotation>, List<Method>> annotationMethods;

    public static TestReport runTest(Class<?> clazz) throws Exception {
        initMapAnnotationMethods(clazz);

        invokeSuiteMethod(BeforeSuite.class);

        TestAnnotationHandler testAnnotationHandler = new TestAnnotationHandler();
        TestReport result = testAnnotationHandler.handle(annotationMethods.get(BeforeTest.class),
                annotationMethods.get(Test.class),
                annotationMethods.get(AfterTest.class),
                clazz.getDeclaredConstructor().newInstance());

        invokeSuiteMethod(AfterSuite.class);

        return result;
    }

    private static void initMapAnnotationMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        List<Class<? extends Annotation>> annotations =
                List.of(AfterSuite.class, AfterTest.class, BeforeTest.class, BeforeSuite.class, Test.class);
        annotationMethods = annotations.stream()
                .collect(Collectors.toMap(
                        annotationClass -> annotationClass,
                        annotationClass -> Arrays.stream(methods)
                                .filter(method -> method.isAnnotationPresent(annotationClass))
                                .toList()
                ));
    }

    private static void invokeSuiteMethod(Class<? extends Annotation> clazz) throws InvocationTargetException, IllegalAccessException {
        List<Method> methods = annotationMethods.get(clazz);
        if (methods.size() > 1) {
            throw new RuntimeException(clazz.toString() + " annotation must be submit once");
        }
        if (methods.size() == 1) {
            Method method = methods.getFirst();
            if (Modifier.isStatic(method.getModifiers())) {
                method.invoke(null);
            } else {
                throw new RuntimeException("Method " + method.getName() + " must be static");
            }
        }
    }
}
