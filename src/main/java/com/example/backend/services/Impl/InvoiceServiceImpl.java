package com.example.backend.services.Impl;

import com.example.backend.dto.InvoiceItemResponse;
import com.example.backend.models.entity.Cart;
import com.example.backend.models.entity.Invoice;
import com.example.backend.models.entity.InvoiceDetail;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.InvoiceDetailRepo;
import com.example.backend.repository.InvoiceRepo;
import com.example.backend.services.CartService;
import com.example.backend.services.InvoiceService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final CartRepository cartRepository;
    private final InvoiceDetailRepo invoiceDetailRepo;



    @Transactional
    @Override
    public Invoice succeed(Long customerId) {
        List<Cart> carts = cartRepository.findAllByCustomer_Id(customerId);
        Invoice theInvoice = new Invoice();
        List<InvoiceDetail> listCart = new ArrayList<>();
        double sum = 0;
        for (Cart cart : carts) {
            listCart.add(InvoiceDetail.builder().quantity(cart.getQuantity()).furniture(cart.getFurniture()).invoice(theInvoice).build());
            sum += cart.getQuantity()*cart.getFurniture().getPrice();
            cartRepository.deleteById(cart.getId());
        }
        theInvoice.setPrice(sum);
        theInvoice.setCustomer_id(customerId);
        theInvoice.setDayVisited(LocalDate.now());
        theInvoice.setInvoiceDetail(listCart);
         return invoiceRepo.save(theInvoice);
    }

    public Invoice getInvoice(Long invoiceId){
        return invoiceRepo.findById(invoiceId).get();
    }

    public List<InvoiceItemResponse> getAllInvoice(Long customerId){
        return invoiceRepo.findByCustomer_id(customerId).stream()
                .map(item -> new InvoiceItemResponse(
                        Math.toIntExact(item.getId()),
                        item.getPrice(),
                        item.getInvoiceDetail().isEmpty() ? null : item.getInvoiceDetail().get(0))
                )
                .collect(Collectors.toList());
    }

}
