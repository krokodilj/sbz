package com.java.sbz.repository;

import com.java.sbz.models.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sirko on 9/2/17.
 */
public interface UserCategoryRepository extends JpaRepository<UserCategory,Long> {

    UserCategory findOneByName(String name);

}
