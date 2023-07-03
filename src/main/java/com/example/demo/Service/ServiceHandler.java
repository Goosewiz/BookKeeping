package com.example.demo.Service;

import java.util.Date;

public interface ServiceHandler {
    void addInfo(String category, double price);
    double getCategories(String name);
    //double[] getInfoOnTime(Date dateFirst, Date dateSecond);
}
