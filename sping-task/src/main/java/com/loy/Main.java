package com.loy;


import com.loy.model.Product;
import com.loy.model.ProductType;
import com.loy.model.User;
import com.loy.service.ProductService;
import com.loy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void run(String... args) {
        System.out.println("Crud Users");
        System.out.println("Get all users: " + userService.getAllUsers());
        System.out.println("User with id 2: " + userService.getUser(2));
        System.out.println("Update user with id 4: " + userService.updateUser(new User(4L, "Danil Dolgikh", new HashSet<>())));
        System.out.println("Get all users: " + userService.getAllUsers());

        System.out.println("///////////");

        System.out.println("Crud Products");
        System.out.println("Get all products: " + productService.getAllProducts());
        System.out.println("Product with id 2: " + productService.getProduct(2));
        productService.deleteProduct(new Product(3L, 123456780L, 300L, ProductType.ACCOUNT, new User(2L, "Ivanov Ivan2", new HashSet<>())));
        System.out.println("Product with id 2 deleted");
        System.out.println("Get all products: " + productService.getAllProducts());
        System.out.println("Update product with id 4: " + productService.updateProduct(
                new Product(4L, 123456782L, 400L, ProductType.ACCOUNT, new User(3L, "Ivanov Ivan3", new HashSet<>()))));
        System.out.println("Get all products: " + productService.getAllProducts());
        System.out.println("Get all products with userId 1: " + productService.getAllByUserId(1));
    }
}