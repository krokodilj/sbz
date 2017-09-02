package com.java.sbz.models;

import javax.persistence.*;

/**
 * Created by sirko on 9/2/17.
 */


@Entity
@Table(name="spending_limit")
public class SpendingLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double upperLimit;

    private Double lowerLimit;

    private Double percent;

    public SpendingLimit(){}

    public SpendingLimit(Double upperLimit,Double lowerLimit,Double percent){
        this.upperLimit=upperLimit;
        this.lowerLimit=lowerLimit;
        this.percent=percent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}
