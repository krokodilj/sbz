package com.java.sbz.services;

import com.java.sbz.dtos.addSaleEventDTO;
import com.java.sbz.models.ArticleCategory;
import com.java.sbz.models.SaleEvent;
import com.java.sbz.repository.ArticleCategoryRepository;
import com.java.sbz.repository.SaleEventRepository;
import com.java.sbz.util.ServiceReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirko on 9/4/17.
 */
@Service
public class SaleEventService {

    @Autowired
    private SaleEventRepository saleEventRepository;

    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    public ServiceReturn getSaleEvents(){
        try{
            List<SaleEvent> data=saleEventRepository.findAll();

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }
    public ServiceReturn addSaleEvent(addSaleEventDTO data){
        try{
            SaleEvent cs=saleEventRepository.findOne(data.getId());
            if(cs!=null) return new ServiceReturn(false,"sale event id taken");

            SaleEvent se=new SaleEvent(data);
            se.setArticleCategories(new ArrayList<ArticleCategory>());
            
            //add article categories
            for(Long acid:data.getArticleCategoriesIds()){
                ArticleCategory ac=articleCategoryRepository.findOne(acid);
                if (ac==null) return  new ServiceReturn(false,"article category not found");
                se.getArticleCategories().add(ac);
            }

            saleEventRepository.save(se);
            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn updateSaleEvent(Long articleCategoryId,addSaleEventDTO data){
        try{
            SaleEvent cs=saleEventRepository.findOne(articleCategoryId);

            if(cs==null) return new ServiceReturn(false,"sale event doesn't exists");

            SaleEvent se=new SaleEvent(data);

            //add article categories
            for(Long acid:data.getArticleCategoriesIds()){
                ArticleCategory ac=articleCategoryRepository.findOne(acid);
                if (ac==null) return  new ServiceReturn(false,"article category not found");
                se.getArticleCategories().add(ac);
            }

            saleEventRepository.save(se);
            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }


}
