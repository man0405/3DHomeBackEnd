package com.example.backend.repository;

import com.example.backend.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Customer findByUser_Id(Long userId);


}
