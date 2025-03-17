package com.loy.limits.api;

import com.loy.limits.service.LimitAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/limit")
@RequiredArgsConstructor
public class LimitAdminRestController {
    private final LimitAdminService limitAdminService;

    @PostMapping("/{amount}")
    public void setDefaultLimit(@PathVariable("amount") long amount) {
        limitAdminService.setNewLimit(amount);
    }
}
