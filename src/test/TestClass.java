package test;

import annotation.*;


public class TestClass {

    @BeforeSuite
    public static void beforeAll() {
        System.out.println("Execute beforeAll");
    }

    @AfterSuite
    public static void afterAll() {
        System.out.println("Execute afterAll");
    }

    @BeforeTest
    public static void beforeEach() {
        System.out.println("Execute beforeEach");
    }

    @AfterTest
    public static void afterEach() {
        System.out.println("Execute afterEach");
    }

    @Test(priority = 3)
    public void firstTest() {
        System.out.println("Execute firstTest");
    }

    @Test
    public void secondTest() {
        System.out.println("Execute secondTest");
    }

    @Test(priority = 7)
    @CsvSource(params = "10, Java, 20, true")
    public void thirdTest(int integer1, String java, int integer2, boolean bool) {
        System.out.println(integer1 + ", " + java + ", " + integer2 + ", " + bool);
        System.out.println("Execute thirdTest");
    }

    @Test(priority = 10)
    public void fourTest() {
        System.out.println("Execute fourTest");
        throw new RuntimeException("test");
    }
}
