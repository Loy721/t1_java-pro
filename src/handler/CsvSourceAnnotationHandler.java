package handler;

import annotation.CsvSource;

import java.lang.reflect.Method;

public class CsvSourceAnnotationHandler {

    private final String SEPARATOR = ", ";

    public Object[] handle(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String csvParams = method.getAnnotation(CsvSource.class).params();
        return parseArgs(parameterTypes, csvParams);
    }

    private Object[] parseArgs(Class<?>[] argTypes, String csvParams) {
        Object[] result = new Object[argTypes.length];
        String[] stringParams = csvParams.split(SEPARATOR);
        if (argTypes.length != stringParams.length)
            throw new RuntimeException("Failed parse args in CsvSource annotation");
        for (int i = 0; i < argTypes.length; i++) {
            result[i] = convertToParameterType(argTypes[i], stringParams[i]);
        }
        return result;
    }


    public static Object convertToParameterType(Class<?> type, String value) {
        if (type == int.class) {
            return Integer.parseInt(value);
        } else if (type == boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (type == String.class) {
            return value;
        }
        throw new IllegalArgumentException("Unsupported parameter type: " + type);
    }
}
