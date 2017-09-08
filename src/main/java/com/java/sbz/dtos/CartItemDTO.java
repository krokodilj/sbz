package com.java.sbz.dtos;

/**
 * Created by sirko on 9/7/17.
 */
public class CartItemDTO {

    private Long id;
    private Integer number;


    public CartItemDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
