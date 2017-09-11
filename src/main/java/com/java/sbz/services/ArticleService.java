package com.java.sbz.services;

import com.java.sbz.dtos.addArticleDTO;
import com.java.sbz.models.Article;
import com.java.sbz.models.ArticleCategory;
import com.java.sbz.models.User;
import com.java.sbz.repository.ArticleCategoryRepository;
import com.java.sbz.repository.ArticleRepository;
import com.java.sbz.util.ServiceReturn;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

    public ServiceReturn getArticles(Long id,String name,Double min,Double max,Long category){
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

            if(article==null) return new ServiceReturn(false,"article doesn't exists");

            article.setCount(data.getCount());

            if(article.getCount()>article.getMinimumCount()) article.setStatus(false);

            articleRepository.save(article);

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn getLowArticles(){
        try{
            List<Article> data=articleRepository.findAllByStatus(false);

            return new ServiceReturn(true,null,data);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }

    public ServiceReturn uploadPicture(MultipartFile file, Long articleId){
        try{
            Article article=articleRepository.findOne(articleId);

            if(article==null) return new ServiceReturn(false,"user doesn't exists");

            //////////upload
            String path="static/images";
            int read = 0;
            byte[] bytes = new byte[1024];

            InputStream is=file.getInputStream();

            OutputStream outpuStream = new FileOutputStream(new File(path+File.separator+articleId+".png"));
            while ((read = is.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            is.close();
            outpuStream.flush();
            outpuStream.close();
            ///////////

            article.setImg_src("images/"+articleId+".png");
            articleRepository.save(article);

            return new ServiceReturn(true,null);
        }catch(Exception e){
            e.printStackTrace();
            return new ServiceReturn(false,"server error");
        }
    }
}
