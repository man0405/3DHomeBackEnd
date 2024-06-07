package com.example.backend.dto;

import com.example.backend.models.entity.Furniture;
import com.example.backend.models.entity.Invoice;
import com.example.backend.models.entity.InvoiceDetail;

public record InvoiceItemResponse(
    int id,
    double price,
    InvoiceDetail invoiceDetail
) {
}
