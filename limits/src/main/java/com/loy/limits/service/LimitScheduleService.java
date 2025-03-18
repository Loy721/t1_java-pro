package com.loy.limits.service;

import com.loy.limits.config.properties.LimitProperties;
import com.loy.limits.dao.LimitRepository;
import com.loy.limits.model.Limit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitScheduleService {
    private final LimitRepository limitRepository;
    private final LimitProperties limitProperties;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetLimit() {
        List<Limit> limits = limitRepository.findAll();
        limits.forEach(l -> l.setLimit(limitProperties.getDefaultLimit()));
    }
}
