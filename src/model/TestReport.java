package model;

public class TestReport {
    private final int countTests;
    private int countFailuresTests;

    public TestReport(int countTests) {
        this.countTests = countTests;
        this.countFailuresTests = 0;
    }

    public int incFailureTest() {
        return  ++countFailuresTests;
    }

    @Override
    public String toString() {
        return "Completed unsuccessfully " + countFailuresTests + " out of " +  countTests + " tests";
    }
}
