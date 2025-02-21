package com.loy;


import com.loy.model.User;
import com.loy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    public void run(String... args) {
        System.out.println("Get all users: " + userService.getAllUsers());
        System.out.println("User with id 2: " + userService.getUser(2));
        userService.deleteUser(new User(2L, "Ivanov Ivan2"));
        System.out.println("User with id 2 deleted");
        System.out.println("Get all users: " + userService.getAllUsers());
        System.out.println("Update user with id 4: " + userService.updateUser(new User(4L, "Danil Dolgikh")));
        System.out.println("Get all users: " + userService.getAllUsers());
    }
}