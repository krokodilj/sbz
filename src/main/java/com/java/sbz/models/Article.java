package com.java.sbz.models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sirko on 9/3/17.
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    private Long id;
    private String name;
    @ManyToOne
    private ArticleCategory category;
    private Double price;
    private Integer count;
    private Date created;
    private Boolean status;
    private Integer minimumCount;

    public Article(){}

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

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getMinimumCount() {
        return minimumCount;
    }

    public void setMinimumCount(Integer minimumCount) {
        this.minimumCount = minimumCount;
    }
}
