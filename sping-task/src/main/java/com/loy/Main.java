package com.loy;


import com.loy.model.User;
import com.loy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.loy");

        UserService userService = context.getBean("userService", UserService.class);

        for (int i = 1; i < 5; i++) {
            System.out.println("User created: " + userService.createUser(new User(1, "Ivanov Ivan" + i)));
        }

        System.out.println("Get all users: " + userService.getAllUsers());
        System.out.println("User with id 2: " + userService.getUser(2));
        System.out.println("Delete user with id 2: " + userService.deleteUser(new User(2, "Ivanov Ivan2")));
        System.out.println("Get all users: " + userService.getAllUsers());
        System.out.println("Update user with id 4: " + userService.updateUser(new User(4, "Danil Dolgikh")));
        System.out.println("Get all users: " + userService.getAllUsers());
        System.out.println("Try delete user with not exist id: " + userService.deleteUser(new User(1000, "test test")));
    }
}