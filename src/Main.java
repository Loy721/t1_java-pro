import model.Employee;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
    }

    public static void task1() {
        System.out.println("Task 1");
        System.out.println(Stream.of(1, 2, 2, 3, 3, 3).distinct().toList());
    }

    public static void task2() {
        System.out.println("Task 2");
        System.out.println(Stream.of(5, 2, 10, 9, 4, 3, 10, 1, 13).sorted(comparingInt(Integer::intValue)
                .reversed()).skip(2).findFirst().orElseThrow());
    }

    public static void task3() {
        System.out.println("Task 3");
        System.out.println(Stream.of(5, 2, 10, 9, 4, 3, 10, 1, 13).distinct().sorted(comparingInt(Integer::intValue)
                .reversed()).skip(2).findFirst().orElseThrow());
    }

    public static void task4() {
        System.out.println("Task 4");
        generateStreamEmployees().filter(e -> e.getPost().equals("Инженер")).sorted(comparingInt(Employee::getAge)
                .reversed()).limit(3).forEach(System.out::println);
    }

    public static void task5() {
        System.out.println("Task 5");
        System.out.println(generateStreamEmployees().filter(e -> e.getPost().equals("Инженер")).mapToInt(Employee::getAge)
                .average().orElseThrow());
    }

    public static void task6() {
        System.out.println("Task 6");
        System.out.println(Stream.of("a", "aaa", "abc", "ds", "dfcsf").max(comparingInt(String::length)).orElseThrow());
    }

    public static void task7() {
        System.out.println("Task 7");
        String string = "asd fdger fz fdsa fgtr e a a a a a a ";
        System.out.println(Arrays.stream(string.split(" ")).collect(groupingBy(k -> k, counting())));
    }

    public static void task8() {
        System.out.println("Task 8");
        Stream.of("a", "abc", "aaa", "ds", "dfcsf").sorted((s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);
            }
            return Integer.compare(s1.length(), s2.length());
        }).forEach(System.out::println);
    }

    public static void task9() {
        System.out.println("Task 9");
        String result = Stream.of("a cd bgf ges df", "t v affds trrg sa", "q hi set get setter")
                .flatMap(s -> Arrays.stream(s.split(" "))).max(comparingInt(String::length)).orElseThrow();
        System.out.println(result);
    }


    private static Stream<Employee> generateStreamEmployees() {
        return Stream.of(new Employee("Ivan", 24, "Инженер"),
                new Employee("Bob", 21, "Инженер"),
                new Employee("Stepan", 29, "Рабочий"),
                new Employee("Danil", 22, "Инженер"),
                new Employee("Kirill", 56, "Инженер")
        );
    }
}