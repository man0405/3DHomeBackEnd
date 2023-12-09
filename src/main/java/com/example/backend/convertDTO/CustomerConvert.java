package com.example.backend.convertDTO;

import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.models.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConvert {
    public CustomerProfile toCustomerProfile(Customer customer){
        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setFirstname(customer.getFirstName());
        customerProfile.setLastname(customer.getLastName());
        customerProfile.setDob(customer.getDob());
        customerProfile.setCountry(customer.getCountry());
        return customerProfile;
    }
    public CustomerPhone toCustomerPhone(Customer customer){
        CustomerPhone customerPhone = new CustomerPhone();
        customerPhone.setPhone(customer.getPhone());
        return customerPhone;
    }

    public Customer toCustomer(CustomerProfile customerProfile, Customer customer){
        customer.setId(customerProfile.getId());
        customer.setFirstName(customerProfile.getFirstname());
        customer.setLastName(customerProfile.getLastname());
        customer.setDob(customerProfile.getDob());
        customer.setCountry(customerProfile.getCountry());
        return customer;
    }
    public Customer toCustomer(CustomerPhone customerPhone, Customer customer){
        customer.setId(customerPhone.getId());
        customer.setPhone(customerPhone.getPhone());
        return customer;
    }
    public Customer toCustomer(CustomerPassword customerPassword, Customer customer){
        customer.setId(customerPassword.getId());
        customer.getUser().setPassword(customerPassword.getNewPassword());
        return customer;
    }
    public CustomerPassword toCustomerPassword(Customer customer){
        CustomerPassword customerPassword = new CustomerPassword();
        customerPassword.setOldPassword(customer.getUser().getPassword());
        customerPassword.setNewPassword("");
        customerPassword.setAgainNewPassword("");
        return customerPassword;
    }
    public Customer toCustomer(CustomerPhone customerPhone){
        Customer customer = new Customer();
        customer.setId(customerPhone.getId());
        customer.setPhone(customerPhone.getPhone());
        return customer;
    }
    public Customer toCustomer(CustomerProfile customerProfile){
        Customer customer = new Customer();
        customer.setId(customerProfile.getId());
        customer.setFirstName(customerProfile.getFirstname());
        customer.setLastName(customerProfile.getLastname());
        customer.setDob(customerProfile.getDob());
        customer.setCountry(customerProfile.getCountry());
        return customer;
    }
//    public Customer toC
}
