package com.java.sbz.util;

import com.java.sbz.models.ArticleCategory;
import com.java.sbz.models.SpendingLimit;
import com.java.sbz.models.UserCategory;
import com.java.sbz.repository.ArticleCategoryRepository;
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

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        UserCategory initialCategory = userCategoryRepository.findOneByName("Regular");
        if(initialCategory==null){
            UserCategory category=new UserCategory();
            category.setName("Regular");
            category.setSpendingLimit(new ArrayList<SpendingLimit>());
            category.getSpendingLimit().add(new SpendingLimit(0.0,100.0,0.01));
            category.getSpendingLimit().add(new SpendingLimit(100.0,1000.0,0.1));

            userCategoryRepository.save(category);

            category=new UserCategory();
            category.setName("Silver");
            category.setSpendingLimit(new ArrayList<SpendingLimit>());
            category.getSpendingLimit().add(new SpendingLimit(0.0,100.0,0.01));
            category.getSpendingLimit().add(new SpendingLimit(100.0,1000.0,0.1));

            userCategoryRepository.save(category);

            category=new UserCategory();
            category.setName("Gold");
            category.setSpendingLimit(new ArrayList<SpendingLimit>());
            category.getSpendingLimit().add(new SpendingLimit(0.0,100.0,0.01));
            category.getSpendingLimit().add(new SpendingLimit(100.0,1000.0,0.1));

            userCategoryRepository.save(category);

        }

        ArticleCategory broadConsumption = articleCategoryRepository.findOneByName("broad consumption");
        if(broadConsumption==null){
            ArticleCategory ac=new ArticleCategory();
            ac.setId(Long.parseLong("7643753746587"));
            ac.setName("broad consumption");
            ac.setMaximumDiscount(0.5);
            articleCategoryRepository.save(ac);

        }

    }
}
