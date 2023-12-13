package com.example.backend.services;

import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.models.entity.Customer;

import java.util.Optional;

public interface CustomerService
{

    Customer saveCustomer(Customer customer);
    Customer findById(Long id);
    Customer findByUser_Id(Long id);

    Customer findById(Long id);
    CustomerProfile updateProfile(CustomerProfile customerProfile);
    CustomerPhone updatePhone(CustomerPhone customerPhone);
    CustomerPassword updatePassword(CustomerPassword customerPassword);

}
