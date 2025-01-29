import model.TestReport;
import test.TestClass;

public class Main {
    public static void main(String[] args) throws Exception {
        TestReport testReport = TestRunner.runTest(TestClass.class);
        System.out.println(testReport);
    }
}