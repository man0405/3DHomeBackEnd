package com.example.backend.services.Impl;

import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.House;
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

    @Override
    public CustomerProfile updateProfile(CustomerProfile customerProfile) {
        return null;
    }

    @Override
    public CustomerPhone updatePhone(CustomerPhone customerPhone) {
        return null;
    }

    @Override
    public CustomerPassword updatePassword(CustomerPassword customerPassword) {
        return null;
    }

}
