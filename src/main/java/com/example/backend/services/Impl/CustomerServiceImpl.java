package com.example.backend.services.Impl;

import com.example.backend.models.entity.Customer;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return  customerRepository.save(customer);
    }

    @Override
    public Customer findByUser_Id(Long id) {
        return customerRepository.findByUser_Id(id);
    }
}
