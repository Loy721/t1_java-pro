package com.loy.limits.service;

import com.loy.limits.config.properties.LimitProperties;
import com.loy.limits.dao.LimitRepository;
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
    private final LimitRepository limitRepository;
    private final LimitProperties limitProperties;

    public long getLimitValueByUserId(long userId) {
        return getLimitByUserId(userId).getLimit();
    }

    @Retryable(retryFor = OptimisticLockException.class)
    public long deductLimit(long userId, long amount) {
        Limit targetLimit = getLimitByUserId(userId);
        if (targetLimit.getLimit() < amount) {
            throw new LimitException("Limit is less than the amount to be written off");
        }
        targetLimit.setLimit(targetLimit.getLimit() - amount);
        limitRepository.save(targetLimit);
        return targetLimit.getLimit();
    }

    private Limit getLimitByUserId(long userId) {
        return limitRepository.findByUserId(userId)
                .orElseGet(() -> limitRepository.save(new Limit(limitProperties.getDefaultLimit(), userId)));
    }
}
