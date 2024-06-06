package com.example.backend.repository;

import com.example.backend.models.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepo extends JpaRepository<InvoiceDetail, Integer> {
}
