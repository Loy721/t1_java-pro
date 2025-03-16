package com.loy.paymentcore.dto;

import lombok.*;

@Data
public class ProductDto {
    private Long id;
    private Long accountNumber;
    private Long balance;
    private ProductType productType;
    private User user;
}
