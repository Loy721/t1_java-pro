package com.loy.limits.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentRemoteMockService {

    public void doPay(long amount, long userId) {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Throw exception from payment remote service");
        }
    }
}
