package com.java.sbz.dtos;

import com.java.sbz.models.ArticleCategory;

import java.util.Date;
import java.util.List;

/**
 * Created by sirko on 9/4/17.
 */
public class addSaleEventDTO {

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Double discount;
    private List<Long> articleCategoriesIds;


    public  addSaleEventDTO(){}

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<Long> getArticleCategoriesIds() {
        return articleCategoriesIds;
    }

    public void setArticleCategoriesIds(List<Long> articleCategoriesIds) {
        this.articleCategoriesIds = articleCategoriesIds;
    }
}
