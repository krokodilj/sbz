package com.java.sbz.models;

import com.java.sbz.dtos.CartItemDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirko on 9/7/17.
 */
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Article article;
    private Double articlePrice;
    private Integer amount;
    private Double totalPrice;

    private Double discount;
    private Double price;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemDiscount> discounts=new ArrayList<ItemDiscount>();

    public Item(){}

    public Item(CartItemDTO data,Article a){
        this.article=a;
        this.articlePrice=a.getPrice();
        this.amount=data.getNumber();
        this.totalPrice=data.getNumber()*a.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Double getArticlePrice() {
        return articlePrice;
    }

    public void setArticlePrice(Double articlePrice) {
        this.articlePrice = articlePrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<ItemDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<ItemDiscount> discounts) {
        this.discounts = discounts;
    }
}
