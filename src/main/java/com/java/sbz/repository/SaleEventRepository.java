package com.java.sbz.repository;

import com.java.sbz.models.SaleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sirko on 9/4/17.
 */
public interface SaleEventRepository extends JpaRepository<SaleEvent,Long> {
}
