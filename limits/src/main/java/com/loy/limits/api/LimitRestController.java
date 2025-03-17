package com.loy.limits.api;

import com.loy.limits.service.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/limit")
@RequiredArgsConstructor
public class LimitRestController {
    private final LimitService limitService;

    @GetMapping("/{userId}")
    public long getLimitValue(@PathVariable("userId") long userId) {
        return limitService.getLimitValueByUserId(userId);
    }

    @PatchMapping("/{userId}")
    public long deductLimit(@PathVariable("userId") long userId, @RequestParam("amount") long amount) {
       return limitService.deductLimit(userId, amount);
    }
}
