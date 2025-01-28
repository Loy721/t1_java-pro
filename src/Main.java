import model.TestReport;
import test.TestClass;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestReport testReport = TestRunner.runTest(TestClass.class);
        System.out.println(testReport);
    }
}