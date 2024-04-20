package com.example.backend.services.Impl;

import com.example.backend.models.entity.Cart;
import com.example.backend.models.entity.Invoice;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.InvoiceRepo;
import com.example.backend.services.CartService;
import com.example.backend.services.InvoiceService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final CartRepository cartRepository;



    @Transactional
    @Override
    public Invoice succeed(Integer[] cartId, Long customerId) {
        Invoice theInvoice = new Invoice();
        for(Integer i: cartId){
            Cart theCart = cartRepository.findById(i.longValue()).get();
            theCart.setPaid(true);
            cartRepository.save(theCart);
        }
        System.out.println("hello");
        double sum = 0;
        for(Integer c : cartId){
            Optional<Cart> theCart = cartRepository.findById(Long.valueOf(c));
            sum += (theCart.get().getQuantity() * theCart.get().getFurniture().getPrice());
            theInvoice.addCart(theCart.get());
            cartRepository.deleteById(Long.valueOf(c));
        }
        theInvoice.setPrice(sum);
        theInvoice.setCustomer_id(customerId);
        theInvoice.setDayVisited(LocalDate.now());
        theInvoice.setTimeVisited(LocalTime.now());
         return invoiceRepo.save(theInvoice);
    }

    public Invoice getInvoice(Long invoiceId){
        return invoiceRepo.findById(invoiceId).get();
    }
}
