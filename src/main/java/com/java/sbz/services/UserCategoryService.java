package com.java.sbz.services;

import com.java.sbz.models.UserCategory;
import com.java.sbz.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sirko on 9/2/17.
 */
@Service
public class UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    public UserCategory getInitialCategory(){
        return userCategoryRepository.findOneByName("Normal");
    }

}
