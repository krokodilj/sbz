package com.java.sbz.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sirko on 9/7/17.
 */
@Entity
public class Receipt {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private Double price;
    private Double totalPrice;
    private Double discount;

    private Double pointsSpent;
    private Double pointsEarned;

    @ManyToOne
    private User customer;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Item> items;

    @OneToMany(cascade=CascadeType.ALL)
    private List<OrderDiscount> discounts=new ArrayList<OrderDiscount>();


    private String state;



    public Receipt(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Double getPointsSpent() {
        return pointsSpent;
    }

    public void setPointsSpent(Double pointsSpent) {
        this.pointsSpent = pointsSpent;
    }

    public Double getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(Double pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<OrderDiscount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<OrderDiscount> discounts) {
        this.discounts = discounts;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
