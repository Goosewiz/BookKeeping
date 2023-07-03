package com.example.demo.Model;

import java.text.DateFormat;
import java.util.Date;

public class Payments {
    private String category;
    private double payment;
    //private Date date;
    public Payments(String category, double payment) {//Date date
        this.category = category;
        this.payment = payment;
        //this.date = date;
    }
    public String getCategory(){
        return this.category;
    }
    public double getPayment(){
        return this.payment;
    }
    /*public Date getDate(){
        return this.date;
    }*/
    public void setCategory(String category){
        this.category = category;
    }
    public void setPayment(double payment){
        this.payment = payment;
    }
    /*public void setDate(Date date){
        this.date = date;
    }*/
}
