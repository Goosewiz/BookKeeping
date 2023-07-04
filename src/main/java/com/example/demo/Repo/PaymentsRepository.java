package com.example.demo.Repo;

import com.example.demo.Model.DBPayments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<DBPayments, Long> {
}
