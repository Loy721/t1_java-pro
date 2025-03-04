package com.loy.dao;

import com.loy.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

    @EntityGraph("Product.withUser")
    List<Product> findAllByUser_Id(long userId);

    @EntityGraph("Product.withUser")
    List<Product> findAll();
}
