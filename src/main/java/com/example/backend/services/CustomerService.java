package com.example.backend.services;

import com.example.backend.models.entity.Customer;
import com.example.backend.models.entity.House;

public interface CustomerService
{

    Customer saveCustomer(Customer customer);
    Customer findByUser_Id(Long id);

}
