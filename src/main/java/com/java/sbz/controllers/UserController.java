package com.java.sbz.controllers;

import com.java.sbz.dtos.addUserDTO;
import com.java.sbz.services.UserService;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by sirko on 9/2/17.
 */

@RestController
@RequestMapping(value="api/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes="application/json"
            )
    public ResponseEntity register(@RequestBody addUserDTO data){

        ServiceReturn ret;
        ret=userService.addUser(data);

        if(!ret.isOk()) {
            return new ResponseEntity( ret.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value="/{username}",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity getUser(@PathVariable String username){
        ServiceReturn ret;
        ret=userService.getUser(username);

        if(!ret.isOk()) {
            if(ret.getMessage().equals("server error"))
                return new ResponseEntity(ret.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            if(ret.getMessage().equals("user not found"))
                return new ResponseEntity(ret.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }


}
