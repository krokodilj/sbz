package com.java.sbz.models;

import javax.persistence.*;

/**
 * Created by sirko on 9/1/17.
 */
@Entity
@Table(name="user_profile")
public class UserProfile {

    @Id
    @GeneratedValue
    private long id;
    private String address;
    private double buyingPoints;
    @ManyToOne
    private UserCategory userCategory;

    public UserProfile(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBuyingPoints() {
        return buyingPoints;
    }

    public void setBuyingPoints(double buyingPoints) {
        this.buyingPoints = buyingPoints;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }
}
