package com.java.sbz.controllers;

import com.java.sbz.dtos.ResponseDTO;
import com.java.sbz.dtos.addSaleEventDTO;
import com.java.sbz.models.SaleEvent;
import com.java.sbz.services.SaleEventService;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sirko on 9/4/17.
 */
@RestController
@RequestMapping(value = "api/sale_event")
public class SaleEventController {

    @Autowired
    private SaleEventService saleEventService;

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity getSaleEvents(){
        ServiceReturn ret;
        ret=saleEventService.getSaleEvents();
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public ResponseEntity addSaleEvent(@RequestBody addSaleEventDTO data){
        ServiceReturn ret;
        ret=saleEventService.addSaleEvent(data);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("article category not found"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.BAD_REQUEST);
            if (ret.getMessage().equals("sale event id taken"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{saleEventId}",
            method = RequestMethod.PUT,
            consumes = "application/json"
    )
    public ResponseEntity updateSaleEvent(@RequestBody addSaleEventDTO data, @PathVariable Long saleEventId){
        ServiceReturn ret;
        ret=saleEventService.updateSaleEvent(saleEventId,data);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("article category not found"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.BAD_REQUEST);
            if (ret.getMessage().equals("sale event doesn't exists"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
