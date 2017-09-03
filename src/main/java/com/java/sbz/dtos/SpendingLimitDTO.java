package com.java.sbz.dtos;

/**
 * Created by sirko on 9/3/17.
 */
public class SpendingLimitDTO {

    private Double upperLimit;

    private Double lowerLimit;

    private Double percent;

    public SpendingLimitDTO(){}


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
