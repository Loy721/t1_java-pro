package com.loy.limits.service;

import com.loy.limits.dao.LimitDao;
import com.loy.limits.model.Limit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LimitDataInitializerService  implements CommandLineRunner {
    private final LimitDao limitDao;
    private final LimitAdminService limitAdminService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Start create limits with users id: 1-100");
        for (int i = 1; i < 101 ; i++) {
            limitDao.save(new Limit(limitAdminService.getDefaultLimit(), i));
        }
    }
}
