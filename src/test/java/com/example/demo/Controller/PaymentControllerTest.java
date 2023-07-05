package com.example.demo.Controller;

import com.example.demo.Model.DBPayments;
import com.example.demo.Repo.PaymentsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class PaymentControllerTest {
    @Autowired
    private PaymentsRepository paymentsRepository;
    @Test
    void addInfo() {
        String name = "Taxi";
        double price = 123;
        LocalDate date = LocalDate.now();
        DBPayments record = new DBPayments(name, price, date);
        paymentsRepository.save(record);
        name = "Taxi";
        price = 123;
        date = LocalDate.now();
        record = new DBPayments(name, price, date);
        paymentsRepository.save(record);
        name = "Market";
        price = 123;
        date = LocalDate.now();
        record = new DBPayments(name, price, date);
        paymentsRepository.save(record);
        name = "Food";
        price = 123;
        date = LocalDate.now();
        record = new DBPayments(name, price, date);
        paymentsRepository.save(record);
        name = "Business";
        price = 123;
        date = LocalDate.of(2014, Calendar.FEBRUARY, 11);
        record = new DBPayments(name, price, date);
        paymentsRepository.save(record);
    }

    @Test
    void getCategories() {
        double answer = 0;
        String name = "Taxi";
        for (DBPayments record:
                paymentsRepository.findAll()) {
            if (record.getName().equals(name))
                answer = answer + record.getPrice();
        }
        if (answer!= 0)
            System.out.println(answer);
        else
            System.out.println("Нет такой категории расходов");
    }

    @Test
    void getPriceForDates() {
        double answer = 0;
        String firstS = "2013-01-10";
        String secondS = "2015-01-10";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate first = LocalDate.parse(firstS, formatter);
        LocalDate second = LocalDate.parse(secondS, formatter);
        for (DBPayments record:
                paymentsRepository.findAll()) {
            if (record.getDate().isAfter(first) && record.getDate().isBefore(second))
                answer = answer + record.getPrice();
        }
        if (answer!= 0)
            System.out.println(answer);
        else
            System.out.println("Нет расходоов между этими датами");
    }

    @Test
    void testGetCategories() {
        ArrayList<String> list = new ArrayList<String>();
        for (DBPayments record:
                paymentsRepository.findAll()) {
            if (!list.contains(record.getName()))
                list.add(record.getName());
        }
        if (list.isEmpty())
            System.out.println("Еще нет ни одной категории расходов");
        else{
            String[] answer = new String[list.size()];
            list.toArray(answer);
            for (int i = 0; i<answer.length; i++)
                System.out.println(answer[i]);
        }
    }
}