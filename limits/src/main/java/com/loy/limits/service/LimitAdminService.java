package com.loy.limits.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class LimitAdminService {
    @Value("${limit.default:10000}")
    private long defaultLimit;
    @Value("${limit.default:10000}")
    private volatile long newLimit;

    public void setNewLimit(long newLimit) {
        if (newLimit < 0) {
            throw new IllegalArgumentException("New limit is less then 0");
        }
        this.newLimit = newLimit;
    }

    public void updateDefaultLimit() {
        this.defaultLimit = newLimit;
    }
}
