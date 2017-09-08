package com.java.sbz.repository;

import com.java.sbz.models.Receipt;
import com.java.sbz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sirko on 9/7/17.
 */
public interface OrderRespository extends JpaRepository<Receipt,Long> {

    List<Receipt> findAllByCustomer(User user);

}
