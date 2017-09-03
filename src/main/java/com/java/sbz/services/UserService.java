package com.java.sbz.services;

import com.java.sbz.dtos.addUserDTO;
import com.java.sbz.models.User;
import com.java.sbz.models.UserCategory;
import com.java.sbz.models.UserProfile;
import com.java.sbz.repository.UserRepository;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
            user.setImageSrc("images/guest.png");
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

    public ServiceReturn login(addUserDTO data){
        try{
            User user=userRepository.findOneByUsername(data.getUsername());

            if(user==null) return new ServiceReturn(false,"username doesn't exists");

            if(!user.getPassword().equals(data.getPassword())) return new ServiceReturn(false,"password doesn't match");

            Map<String,String> token =new HashMap<String,String>();
            token.put("username",user.getUsername());
            token.put("role",user.getRole());

            return new ServiceReturn(true,null,token);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn checkUsername(String username){
        try{
            User user=userRepository.findOneByUsername(username);

            if(user!=null) return new ServiceReturn(false,"username not available");

            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn uploadPicture(MultipartFile file,String username){
        try{
            User user=userRepository.findOneByUsername(username);

            if(user==null) return new ServiceReturn(false,"user doesn't exists");

            //////////upload
            String path="static/images";
            int read = 0;
            byte[] bytes = new byte[1024];

            InputStream is=file.getInputStream();

            OutputStream outpuStream = new FileOutputStream(new File(path+File.separator+username+".png"));
            while ((read = is.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            is.close();
            outpuStream.flush();
            outpuStream.close();
            ///////////

            user.setImageSrc("images/"+username+".png");
            userRepository.save(user);

            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

}
