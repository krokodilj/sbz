package com.java.sbz.dtos;

/**
 * Created by sirko on 9/3/17.
 */
public class addArticleCategoryDTO {

    private Long id;
    private String name;
    private Double maximumDiscount;
    private Long parentCategoryId;

    public addArticleCategoryDTO(){}

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

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }



}
