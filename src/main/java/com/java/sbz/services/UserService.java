package com.java.sbz.services;

import com.java.sbz.dtos.addUserDTO;
import com.java.sbz.models.User;
import com.java.sbz.models.UserCategory;
import com.java.sbz.models.UserProfile;
import com.java.sbz.repository.UserRepository;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by sirko on 9/2/17.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCategoryService userCategoryService;

    public ServiceReturn addUser(addUserDTO data){
        try {
            User user = new User(data);
            user.setRegistered(new Date());

            if(user.getRole().equals("customer")){
                //set him a user profile
                UserProfile profile=new UserProfile();
                profile.setBuyingPoints(0.0);
                profile.setAddress(data.getAddress());

                //and set initial user category
                UserCategory category=userCategoryService.getInitialCategory();
                profile.setUserCategory(category);

                user.setUserProfile(profile);
            }

            userRepository.save(user);

            return new ServiceReturn(true,null);

        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }

    }

    public ServiceReturn getUser(String username){
        try{
            User user = userRepository.findOneByUsername(username);

            if(user==null) return new ServiceReturn(false,"user not found");

            return new ServiceReturn(true,null,user);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }


    }


}
