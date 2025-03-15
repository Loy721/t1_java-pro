package com.loy.paymentcore.service;

import com.loy.paymentcore.dto.ProductDto;
import com.loy.paymentcore.exception.BalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final RestTemplate integrationRestTemplate;

    public ProductDto getProduct(long id) {
        return integrationRestTemplate.getForObject("/product/{id}", ProductDto.class, id);
    }

    public ProductDto payProduct(long id, Long amount) throws BalanceException {
        ResponseEntity<ProductDto> productEntity = integrationRestTemplate.getForEntity("/product/{id}", ProductDto.class, id);
        ProductDto productDto = productEntity.getBody();
        if (productDto.getBalance() < amount) {
            throw new BalanceException("Слишком маленький баланс");
        }
        productDto.setBalance(productDto.getBalance() - amount);
        return integrationRestTemplate.patchForObject("/product/{id}", productDto, ProductDto.class, id);
    }
}
