package com.java.sbz.controllers;

import com.java.sbz.dtos.ResponseDTO;
import com.java.sbz.dtos.addUserDTO;
import com.java.sbz.services.UserService;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
            return new ResponseEntity( ret.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
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

    @RequestMapping(
            value="/login",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity login(@RequestBody addUserDTO data){
        ServiceReturn ret;
        ret=userService.login(data);
        if(!ret.isOk()){
            if(ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
            if(ret.getMessage().equals("username doesn't exists"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()),HttpStatus.NOT_FOUND);
            if(ret.getMessage().equals("password doesn't match"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()),HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }

    @RequestMapping(
            value = "/check/{username}",
            method = RequestMethod.GET

    )
    public ResponseEntity checkUsername(@PathVariable String username){
        ServiceReturn ret;
        ret=userService.checkUsername(username);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("username not available"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()),HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST,
            consumes = "multipart/form-data"
    )
    public ResponseEntity uploadPicture(@RequestParam MultipartFile file,@RequestParam String username){
        ServiceReturn ret;
        ret=userService.uploadPicture(file,username);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("user doesn't exists"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(HttpStatus.OK);
    }


}
