package com.example.backend.repository;

import com.example.backend.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("select c from Customer c  where c.user.id = ?1 ")
    Customer findByUser_Id(Long userId);

    @Query("select c from Customer  c where c.id = ?1")
    Customer findCustomerById(Long id);
}
