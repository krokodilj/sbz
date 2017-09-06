package com.java.sbz.controllers;

import com.java.sbz.dtos.ResponseDTO;
import com.java.sbz.dtos.addArticleCategoryDTO;
import com.java.sbz.services.ArticleCategoryService;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sirko on 9/3/17.
 */
@RestController
@RequestMapping(value = "api/article_category")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;


    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity getArticleCategories(){
        ServiceReturn ret;
        ret=articleCategoryService.getArticleCategories();
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
    public ResponseEntity addArticleCategory(@RequestBody addArticleCategoryDTO data){
        ServiceReturn ret;
        ret=articleCategoryService.addArticleCategory(data);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("parent category not found"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.BAD_REQUEST);
            if (ret.getMessage().equals("article category id taken"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.CONFLICT);
            if (ret.getMessage().equals("don't do this"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{articleCategoryId}",
            method = RequestMethod.PUT,
            consumes = "application/json"
    )
    public ResponseEntity addArticleCategory(@RequestBody addArticleCategoryDTO data, @PathVariable Long articleCategoryId){
        ServiceReturn ret;
        ret=articleCategoryService.updateArticleCategory(articleCategoryId,data);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("parent category not found"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.BAD_REQUEST);
            if (ret.getMessage().equals("article category doesn't exists"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
