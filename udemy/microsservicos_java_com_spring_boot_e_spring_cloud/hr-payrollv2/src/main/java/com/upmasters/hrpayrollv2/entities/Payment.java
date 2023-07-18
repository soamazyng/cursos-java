package com.upmasters.hrpayrollv2.entities;

import java.io.Serializable;

public class Payment implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private Double dailyIncome;
  private Integer days;

  public Payment() {
  }

  public Payment(final String name, final Double dailyIncome, final Integer days) {
    this.name = name;
    this.dailyIncome = dailyIncome;
    this.days = days;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Double getDailyIncome() {
    return dailyIncome;
  }

  public void setDailyIncome(final Double dailyIncome) {
    this.dailyIncome = dailyIncome;
  }

  public Integer getDays() {
    return days;
  }

  public void setDays(final Integer days) {
    this.days = days;
  }

  public double getTotal(){
    return days * dailyIncome;
  }

}