package com.example.demo.Controller;

import com.example.demo.Service.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private ServiceHandler serviceHandler;
    public PaymentController(ServiceHandler serviceHandler){
        this.serviceHandler = serviceHandler;
    }
    @PostMapping
    public void addInfo(@RequestParam String name, @RequestParam double price) {
        serviceHandler.addInfo(name, price);
    }
    @GetMapping
    public double getCategories(@RequestParam String name){
        return serviceHandler.getCategories(name);
    }
}
