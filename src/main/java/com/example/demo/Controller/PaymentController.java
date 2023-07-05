package com.example.demo.Controller;

import com.example.demo.Model.DBPayments;
import com.example.demo.Repo.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentsRepository paymentsRepository;
    //Добавление записи в БД
    @PostMapping
    public String addInfo(@RequestParam String name, @RequestParam double price) {
        LocalDate date = LocalDate.now();
        //LocalDate date = LocalDate.of(2014, Calendar.FEBRUARY, 11);
        DBPayments record = new DBPayments(name, price, date);
        paymentsRepository.save(record);
        return "Запись успешно добавлена";
    }
    //Получение суммы расходов по одной категории
    @GetMapping(value = "/", params ={ "name" })
    public double getCategories(@RequestParam(value = "name") String name) throws Exception{
        double answer = 0;
        for (DBPayments record:
                paymentsRepository.findAll()) {
            if (record.getName().equals(name))
                answer = answer + record.getPrice();
        }
        if (answer!= 0)
            return answer;
        else
            throw new Exception("Нет такой категории расходов");
    }
    //Получение суммы расходов между двумя датами
    @GetMapping(value = "/", params = {"firstS", "secondS"})
    public double getPriceForDates(@RequestParam(value = "firstS") String firstS,@RequestParam(value = "secondS") String secondS) throws Exception{
        double answer = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate first = LocalDate.parse(firstS, formatter);
        LocalDate second = LocalDate.parse(secondS, formatter);
        for (DBPayments record:
                paymentsRepository.findAll()) {
            if (record.getDate().isAfter(first) && record.getDate().isBefore(second))
                answer = answer + record.getPrice();
        }
        if (answer!= 0)
            return answer;
        else
            throw new Exception("Нет расходов между этими датами");
    }
    //Получение всех категорий расходов в БД
    @GetMapping(value = "/")
    public String[] getCategories() throws Exception{
        ArrayList<String> list = new ArrayList<String>();
        for (DBPayments record:
                paymentsRepository.findAll()) {
            if (!list.contains(record.getName()))
                list.add(record.getName());
        }
        if (list.isEmpty())
            throw new Exception("Еще нет ни одной категории расходов");
        else{
            String[] answer = new String[list.size()];
            list.toArray(answer);
            return answer;
        }
    }
    //Обработка запроса не по шаблону
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
