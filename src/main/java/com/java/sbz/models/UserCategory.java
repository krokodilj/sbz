package com.java.sbz.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sirko on 9/2/17.
 */
@Entity
@Table(name="user_category")
public class UserCategory {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<SpendingLimit> spendingLimit;

    public UserCategory(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpendingLimit> getSpendingLimit() {
        return spendingLimit;
    }

    public void setSpendingLimit(List<SpendingLimit> spendingLimit) {
        this.spendingLimit = spendingLimit;
    }
}
