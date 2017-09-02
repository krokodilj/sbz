package com.java.sbz.repository;

import com.java.sbz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sirko on 9/2/17.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findOneByUsername(String username);

}
