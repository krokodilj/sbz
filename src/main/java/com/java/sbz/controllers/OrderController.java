package com.java.sbz.controllers;

import com.java.sbz.dtos.CartItemDTO;
import com.java.sbz.dtos.ResponseDTO;
import com.java.sbz.models.Receipt;
import com.java.sbz.services.OrderService;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sirko on 9/7/17.
 */

@RestController
@RequestMapping(value = "api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(
            value = "/getOrderFromCart/{userId}",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public ResponseEntity getOrderFromCart(@RequestBody List<CartItemDTO> data, @PathVariable String userId){
        ServiceReturn ret;
        ret=orderService.getOrderFromCart(userId,data);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public ResponseEntity addOrder(@RequestBody Receipt data){
        ServiceReturn ret;

        ret=orderService.addOrder(data);

        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

            return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }

}
