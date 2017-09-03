package com.java.sbz.services;

import com.java.sbz.dtos.SpendingLimitDTO;
import com.java.sbz.models.SpendingLimit;
import com.java.sbz.models.UserCategory;
import com.java.sbz.repository.SpendingLimitRepository;
import com.java.sbz.repository.UserCategoryRepository;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirko on 9/2/17.
 */
@Service
public class UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Autowired
    private SpendingLimitRepository spendingLimitRepository;

    public UserCategory getInitialCategory(){
        return userCategoryRepository.findOneByName("Regular");
    }

    public ServiceReturn getUserCategories(){
        try{

            List<UserCategory> data=  userCategoryRepository.findAll();

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn updateSpendingLimits(long userCategoryId, List<SpendingLimitDTO> data){
        try{
            UserCategory userCategory=userCategoryRepository.findOne(userCategoryId);

            if(userCategory==null) return new ServiceReturn(false,"user category doesn't exists");

            //ids for deletion
            List<Long> ids= new ArrayList<Long>();
            for(SpendingLimit sl:userCategory.getSpendingLimit()){
                ids.add(sl.getId());
            }

            // add new limits
            List<SpendingLimit> new_limits=new ArrayList<SpendingLimit>();

            for(SpendingLimitDTO nsl:data){
                new_limits.add(new SpendingLimit(nsl));
            }

            userCategory.setSpendingLimit(new_limits);

            userCategoryRepository.save(userCategory);

            //delete old limits

            for(Long id:ids) spendingLimitRepository.delete(id);

            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

}
