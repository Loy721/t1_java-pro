package com.loy.limits.service;

import com.loy.limits.dao.LimitDao;
import com.loy.limits.exception.LimitException;
import com.loy.limits.model.Limit;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LimitService {
    private final LimitDao limitDao;
    private final LimitAdminService limitAdminService;
    private final PaymentRemoteMockService paymentRemoteMockService;

    public long getLimitValueByUserId(long userId) {
        return getLimitByUserId(userId).getLimit();
    }

    @Retryable(retryFor = OptimisticLockException.class)
    public long deductLimit(long userId, long amount) {
        Limit targetLimit = getLimitByUserId(userId);
        if (targetLimit.getLimit() < amount) {
            throw new LimitException("Limit is less than the amount to be written off");
        }
        paymentRemoteMockService.doPay(amount, userId);
        targetLimit.setLimit(targetLimit.getLimit() - amount);
        limitDao.save(targetLimit);
        return targetLimit.getLimit();
    }

    private Limit getLimitByUserId(long userId) {
        return limitDao.findByUserId(userId)
                .orElseGet(() -> limitDao.save(new Limit(limitAdminService.getDefaultLimit(), userId)));
    }
}
