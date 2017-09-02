package com.java.sbz.util;

import com.java.sbz.models.SpendingLimit;
import com.java.sbz.models.UserCategory;
import com.java.sbz.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by sirko on 9/2/17.
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        UserCategory initialCategory = userCategoryRepository.findOneByName("Normal");
        if(initialCategory==null){
            UserCategory category=new UserCategory();
            category.setName("Normal");
            category.setSpendingLimit(new ArrayList<SpendingLimit>());
            category.getSpendingLimit().add(new SpendingLimit(0.0,100.0,0.01));
            category.getSpendingLimit().add(new SpendingLimit(100.0,1000.0,0.1));

            userCategoryRepository.save(category);
        }
    }
}
