package com.example.backend.services;

import com.example.backend.models.entity.Cart;
import com.example.backend.models.entity.Invoice;

import java.util.List;

public interface InvoiceService {

    Invoice succeed(Integer[] cartId, Long customerId);

    Invoice getInvoice(Long invoiceId);

    List<Invoice> getAllInvoice(Long customerId);


}
