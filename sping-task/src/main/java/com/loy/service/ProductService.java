package com.loy.service;

import com.loy.dao.ProductDao;
import com.loy.exception.ProductNotFound;
import com.loy.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public Product createProduct(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("When product create id must be null");
        }
        return productDao.save(product);
    }

    public Product getProduct(long id) {
        return productDao.findById(id).orElseThrow(() -> new ProductNotFound("Product with id: " + id + " not found"));
    }

    public List<Product> getAllByUserId(long userId) {
        return productDao.findAllByUser_Id(userId);
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product updateProduct(Product product) {
        if(product.getId() == null) {
            throw new IllegalArgumentException("When product create id must not be null");
        }
        return productDao.save(product);
    }

    public void deleteProduct(Product product) {
        productDao.delete(product);
    }

}
