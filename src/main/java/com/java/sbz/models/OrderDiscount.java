package com.java.sbz.models;

import javax.persistence.*;

/**
 * Created by sirko on 9/7/17.
 */
@Entity
@Table(name = "order_discount")
public class OrderDiscount {

    @Id
    @GeneratedValue
    private Long id;

    private Double discount;
    private String status;

    public OrderDiscount(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
