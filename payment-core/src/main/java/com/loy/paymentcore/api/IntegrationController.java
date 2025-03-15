package com.loy.paymentcore.api;

import com.loy.paymentcore.dto.ProductDto;
import com.loy.paymentcore.exception.BalanceException;
import com.loy.paymentcore.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class IntegrationController {
    private final PaymentService paymentService;

    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable("id") long id) {
        return paymentService.getProduct(id);
    }

    @PostMapping("/product/{id}")
    public ProductDto payProduct(@PathVariable("id") long id, @RequestParam("amount") long amount) throws BalanceException {
        return paymentService.payProduct(id, amount);
    }
}
