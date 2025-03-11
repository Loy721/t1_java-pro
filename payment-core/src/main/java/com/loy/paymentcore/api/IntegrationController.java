package com.loy.paymentcore.api;

import com.loy.paymentcore.dto.ProductDto;
import com.loy.paymentcore.exception.BalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class IntegrationController {
    private final RestTemplate integrationRestTemplate;

    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable("id") long id) {
        return integrationRestTemplate.getForObject("/product/{id}", ProductDto.class, id);
    }

    @PostMapping("/product/{id}")
    public ProductDto payProduct(@PathVariable("id") long id, @RequestParam("amount") Long amount) throws BalanceException {
        ResponseEntity<ProductDto> productEntity = integrationRestTemplate.getForEntity("/product/{id}", ProductDto.class, id);
        ProductDto productDto = productEntity.getBody();
        if (productDto.getBalance() < amount) {
            throw new BalanceException("Слишком маленький баланс");
        }
        productDto.setBalance(productDto.getBalance() - amount);
        return integrationRestTemplate.patchForObject("/product/{id}", productDto, ProductDto.class, id);
    }
}
