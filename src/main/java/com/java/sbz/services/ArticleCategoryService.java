package com.java.sbz.services;

import com.java.sbz.dtos.addArticleCategoryDTO;
import com.java.sbz.models.ArticleCategory;
import com.java.sbz.repository.ArticleCategoryRepository;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sirko on 9/3/17.
 */
@Service
public class ArticleCategoryService {

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;


    public ServiceReturn getArticleCategories(){
        try{
            List<ArticleCategory> data=articleCategoryRepository.findAll();

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }
    public ServiceReturn addArticleCategory(addArticleCategoryDTO data){
        try{
            ArticleCategory cc=articleCategoryRepository.findOne(data.getId());
            if(cc!=null) return new ServiceReturn(false,"article category id taken");

            ArticleCategory ac=new ArticleCategory(data);

            //add broad consumption category as parent if parent id is null
            if(data.getParentCategoryId()==null){
                ArticleCategory bc=articleCategoryRepository.findOneByName("broad consumption");
                ac.setParentCategory(bc);
            }else{
                ArticleCategory pc=articleCategoryRepository.findOne(data.getParentCategoryId());
                if(pc==null) return new ServiceReturn(false,"parent category not found");
                ac.setParentCategory(pc);
            }
            articleCategoryRepository.save(ac);
            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn updateArticleCategory(Long articleCategoryId,addArticleCategoryDTO data){
        try{
            ArticleCategory ac=articleCategoryRepository.findOne(articleCategoryId);

            if(ac==null) return new ServiceReturn(false,"article category doesn't exists");

            ac.setName(data.getName());
            ac.setMaximumDiscount(data.getMaximumDiscount());
            //add broad consumption category as parent if parent id is null
            if(data.getParentCategoryId()==null){
                ArticleCategory bc=articleCategoryRepository.findOneByName("broad consumption");
                ac.setParentCategory(bc);
            }else{
                ArticleCategory pc=articleCategoryRepository.findOne(data.getParentCategoryId());
                if(pc==null) return new ServiceReturn(false,"parent category not found");
                ac.setParentCategory(pc);
            }

            articleCategoryRepository.save(ac);
            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }


}
