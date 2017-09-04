package com.java.sbz.models;

import com.java.sbz.dtos.addSaleEventDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by sirko on 9/4/17.
 */
@Entity
@Table(name="sale_event")
public class SaleEvent {

    @Id
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Double discount;
    @ManyToMany
    private List<ArticleCategory> articleCategories;


    public SaleEvent(){

    }

    public SaleEvent(addSaleEventDTO data){
        this.id=data.getId();
        this.name=data.getName();
        this.discount=data.getDiscount();
        this.startDate=data.getStartDate();
        this.endDate=data.getEndDate();
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

    public List<ArticleCategory> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(List<ArticleCategory> articleCategories) {
        this.articleCategories = articleCategories;
    }
}
