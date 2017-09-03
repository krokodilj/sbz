package com.java.sbz.repository;

import com.java.sbz.models.SpendingLimit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sirko on 9/3/17.
 */
public interface SpendingLimitRepository extends JpaRepository<SpendingLimit,Long> {
}
