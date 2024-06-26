package com.example.backend.repository;

import com.example.backend.models.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    @Query(value = "SELECT c FROM Invoice c where c.customer_id = ?1 ")
    List<Invoice> findByCustomer_id(Long customerId);

}
