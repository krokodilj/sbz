package com.java.sbz.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.sbz.dtos.addUserDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by sirko on 9/1/17.
 */
@Entity
@Table(name="user")
public class User {

    @Id
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String imageSrc;
    private Date registered;
    @OneToOne(cascade = {CascadeType.ALL})
    private UserProfile userProfile;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Receipt> orders;

    public User(){}

    public User(addUserDTO data){
        this.username=data.getUsername();
        this.password=data.getPassword();
        this.firstName=data.getFirstName();
        this.lastName=data.getLastName();
        this.role=data.getRole();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public List<Receipt> getOrders() {
        return orders;
    }

    public void setOrders(List<Receipt> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", registered=" + registered +
                ", userProfile=" + userProfile +
                '}';
    }
}
