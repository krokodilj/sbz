package com.java.sbz.services;

import com.java.sbz.dtos.addArticleDTO;
import com.java.sbz.models.Article;
import com.java.sbz.models.ArticleCategory;
import com.java.sbz.repository.ArticleCategoryRepository;
import com.java.sbz.repository.ArticleRepository;
import com.java.sbz.util.ServiceReturn;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by sirko on 9/6/17.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Autowired
    private KieContainer kieContainer;

    public ServiceReturn getArticles(Long id,String name,Integer min,Integer max,Long category){
        try{


            List<Article> data=articleRepository.search(id,name,min,max,category);

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn addArticle(addArticleDTO data){
        try{
            Article article=new Article(data);

            article.setCreated(new Date());
            article.setStatus(true);

            ArticleCategory ac=articleCategoryRepository.findOne(data.getCategoryId());
            if(ac==null) return new ServiceReturn(false,"category not found");
            article.setCategory(ac);

            articleRepository.save(article);

            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn updateArticle(addArticleDTO data,Long articleId){
        try{
            Article article=articleRepository.findOne(articleId);

            if(article==null) return new ServiceReturn(false,"sale event doesn't exists");

            article.setCount(data.getCount());

            articleRepository.save(article);

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }



}
