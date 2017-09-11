package com.java.sbz.controllers;

import com.java.sbz.dtos.ResponseDTO;
import com.java.sbz.dtos.addArticleDTO;
import com.java.sbz.services.ArticleService;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sirko on 9/6/17.
 */
@RestController
@RequestMapping(value = "api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity getArticle(
                @RequestParam(required = false) Long id,
                @RequestParam(required = false ) String name,
                @RequestParam(required = false ) Double min,
                @RequestParam(required = false ) Double max,
                @RequestParam(required = false ) Long category)
    {
        ServiceReturn ret;
        ret=articleService.getArticles(id,name,min,max,category);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }


    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            produces = "application/json"
    )
    public ResponseEntity addArticle(@RequestBody addArticleDTO data)
    {
        ServiceReturn ret;
        ret=articleService.addArticle(data);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("category not found"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{articleId}",
            method = RequestMethod.PUT,
            produces = "application/json"
    )
    public ResponseEntity updateArticleCategories(@RequestBody addArticleDTO data,@PathVariable Long articleId)
    {
        ServiceReturn ret;
        ret=articleService.updateArticle(data,articleId);
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            if (ret.getMessage().equals("category not found"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/low",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    public ResponseEntity getArticle()
    {
        ServiceReturn ret;
        ret=articleService.getLowArticles();
        if(!ret.isOk()) {
            if (ret.getMessage().equals("server error"))
                return new ResponseEntity(new ResponseDTO(ret.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(ret.getData(),HttpStatus.OK);
    }
}
