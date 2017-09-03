package com.java.sbz.models;

import com.java.sbz.dtos.addArticleCategoryDTO;

import javax.persistence.*;

/**
 * Created by sirko on 9/3/17.
 */
@Entity
@Table(name = "article_category")
public class ArticleCategory {

    @Id
    private Long id;
    private String name;
    private Double maximumDiscount;
    @ManyToOne
    private ArticleCategory parentCategory;



    public ArticleCategory(){};

    public ArticleCategory(addArticleCategoryDTO data){
        this.id=data.getId();
        this.name=data.getName();
        this.maximumDiscount=data.getMaximumDiscount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaximumDiscount() {
        return maximumDiscount;
    }

    public void setMaximumDiscount(Double maximumDiscount) {
        this.maximumDiscount = maximumDiscount;
    }

    public ArticleCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(ArticleCategory parentCategory) {
        this.parentCategory = parentCategory;
    }
}
