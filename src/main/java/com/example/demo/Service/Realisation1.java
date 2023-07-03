package com.example.demo.Service;

import com.example.demo.Model.Payments;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class Realisation1 implements ServiceHandler{
    private Map<String, Double> NotDB = new HashMap<>();
    @Override
    public void addInfo(String category, double price) {
        NotDB.put(category, price);
    }

   @Override
    public double getCategories(String name) {
        return NotDB.get(name);
    }
}
