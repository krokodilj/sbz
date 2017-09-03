package com.java.sbz.controllers;

import com.java.sbz.dtos.SpendingLimitDTO;
import com.java.sbz.models.SpendingLimit;
import com.java.sbz.services.UserCategoryService;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sirko on 9/3/17.
 */

@RestController

@RequestMapping(value="api/user_category")
public class UserCategoryController {

    @Autowired
    private UserCategoryService userCategoryService;


    @RequestMapping(
            value="",
            method = RequestMethod.GET
    )
    public ResponseEntity getCategories(){
        ServiceReturn ret;
        ret=userCategoryService.getUserCategories();

        if(!ret.isOk()) {
            return new ResponseEntity( ret.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }

    @RequestMapping(
            value="/{userCategoryId}",
            method = RequestMethod.PUT,
            consumes = "application/json"
    )
    public ResponseEntity updateCategory(@PathVariable long userCategoryId,@RequestBody List<SpendingLimitDTO> data){
        ServiceReturn ret;
        ret=userCategoryService.updateSpendingLimits(userCategoryId,data);

        if(!ret.isOk()) {
            if(ret.getMessage().equals("user category doesn't exists"))
                return new ResponseEntity( ret.getMessage(), HttpStatus.NOT_FOUND);
            if(ret.getMessage().equals("server error"))
                return new ResponseEntity( ret.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }


}
