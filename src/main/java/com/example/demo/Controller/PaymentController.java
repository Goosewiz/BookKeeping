package com.example.demo.Controller;

import com.example.demo.Model.DBPayments;
import com.example.demo.Repo.PaymentsRepository;
import com.example.demo.Service.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class PaymentController {
    @Autowired
    private ServiceHandler serviceHandler;
    @Autowired
    private PaymentsRepository paymentsRepository;
    public PaymentController(ServiceHandler serviceHandler){
        this.serviceHandler = serviceHandler;
    }
    @PostMapping
    public void addInfo(@RequestParam String name, @RequestParam double price) {
        serviceHandler.addInfo(name, price);
        Date now = new Date();
        DBPayments record = new DBPayments(name, price, now);
        paymentsRepository.save(record);
    }
    @GetMapping
    public double getCategories(@RequestParam String name){
        //Iterable<DBPayments> allPayments = paymentsRepository.findAll();
        return serviceHandler.getCategories(name);
    }
}
