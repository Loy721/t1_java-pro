package com.loy.limits.service;

import com.loy.limits.dao.LimitDao;
import com.loy.limits.model.Limit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitScheduleService {
    private final LimitDao limitDao;
    private final LimitAdminService limitAdminService;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetLimit() {
        limitAdminService.updateDefaultLimit();
        List<Limit> limits = limitDao.findAll();
        limits.forEach(l -> l.setLimit(limitAdminService.getDefaultLimit()));
    }
}
