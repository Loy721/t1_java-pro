package com.loy.api;

import com.loy.model.Product;
import com.loy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }

    @PatchMapping("/{id}")
    public Product update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("user/{id}")
    public List<Product> getProductByUserId(@PathVariable("id") long id) {
        return productService.getAllByUserId(id);
    }
}
