package com.loy.limits.dao;

import com.loy.limits.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitDao extends JpaRepository<Limit, Long> {
    Optional<Limit> findByUserId(long userId);
}
