package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtil {

    public static Method getMethodWithSingleAnnotation(Class<? extends Annotation> annotationClass, Method[] Methods) {
        List<Method> list = Arrays.stream(Methods).filter(x -> x.isAnnotationPresent(annotationClass)).toList();
        if (list.size() > 1) {
            throw new RuntimeException(annotationClass.toString() + " annotation must be submit once");
        }
        if (list.size() == 1) {
            return list.getFirst();
        }
        return null;
    }
}
