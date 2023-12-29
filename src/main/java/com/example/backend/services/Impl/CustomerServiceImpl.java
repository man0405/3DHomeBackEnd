package com.example.backend.services.Impl;

import com.example.backend.convertDTO.CustomerConvert;
import com.example.backend.dto.CustomerPassword;
import com.example.backend.dto.CustomerPhone;
import com.example.backend.dto.CustomerProfile;
import com.example.backend.exception.CustomMessageException;
import com.example.backend.models.entity.Customer;
import com.example.backend.repository.CustomerRepository;
import com.example.backend.services.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustomerConvert customerConvert;
    private PasswordEncoder passwordEncoder;


    @Override
    public Customer saveCustomer(Customer customer) {
        return  customerRepository.save(customer);
    }

    @Override
    public Customer findByUser_Id(Long id) {
        return customerRepository.findByUser_Id(id);
    }

    @Override
    public Customer findById(Long id) {
//        return customerRepository.findCustomerById(id);
        return customerRepository.findCustomerById(id);
    }

    public Optional<Customer> customer(Long id) {
//        return customerRepository.findCustomerById(id);
        return customerRepository.findById(id);
    }

    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    @Override
    public CustomerProfile updateProfile(CustomerProfile customerProfile) {
        Customer oldCustomer = customerRepository.findById(customerProfile.getId()).orElseThrow(
                ()-> new IllegalStateException("Customer with id = "+customerProfile.getId()+" does not exists"));
        Customer newCustomer =  customerConvert.toCustomer(customerProfile,oldCustomer);

        if (newCustomer.getFirstName()!=null && newCustomer.getLastName()!=null && !newCustomer.getFirstName().equals(oldCustomer.getFirstName()) && !newCustomer.getLastName().equals(oldCustomer.getLastName()))
            newCustomer = customerRepository.save(newCustomer);
        return customerConvert.toCustomerProfile(newCustomer);
    }
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    @Override
    public CustomerPhone updatePhone(CustomerPhone customerPhone) {
        Customer newCustomer = new Customer();
        Customer oldCustomer = new Customer();
        oldCustomer = customerRepository.findById(customerPhone.getId()).orElseThrow(()-> new IllegalStateException("ID = "+customerPhone.getId()+" does not exists"));
        newCustomer = customerConvert.toCustomer(customerPhone,oldCustomer);

        if (isValidPhoneNumber(newCustomer.getPhone())){
            newCustomer = customerRepository.save(newCustomer);
        } else {
            new IllegalStateException("Customer with phone number :" +newCustomer.getPhone()+ " is invalid");
        }
//        newCustomer = customerRepository.save(newCustomer);
        return customerConvert.toCustomerPhone(newCustomer);
    }
    @Transactional(rollbackOn = {Exception.class, Throwable.class})
    @Override
    public CustomerPassword updatePassword(CustomerPassword customerPassword) {
        Customer newCustomer = new Customer();
        Customer oldCustomer = new Customer();
        oldCustomer = customerRepository.findById(customerPassword.getId()).orElseThrow(()-> new IllegalStateException("ID = "+customerPassword.getId()+" does not exists"));;
        String password = oldCustomer.getUser().getPassword();
        if(!customerPassword.getNewPassword().equals(customerPassword.getAgainNewPassword())){
            throw  new CustomMessageException("The two passwords do not match", "401");
        }
        if (passwordEncoder.matches(customerPassword.getOldPassword(), password)){
            customerPassword.setNewPassword(passwordEncoder.encode(customerPassword.getNewPassword()));
            newCustomer = customerConvert.toCustomer(customerPassword,oldCustomer);
        } else throw new CustomMessageException("Password is not correct","401");
//        newCustomer = customerRepository.save(newCustomer);
        return customerConvert.toCustomerPassword(newCustomer);
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression pattern for valid phone numbers
        String regex = "^(\\+\\d{1,3})?(\\d{2,12})$";
        // Compile the regular expression pattern
        Pattern pattern = Pattern.compile(regex);
        // Match the phone number against the regular expression pattern
        Matcher matcher = pattern.matcher(phoneNumber);
        // Return true if the phone number is valid, false otherwise
        return matcher.matches();
    }
    public static boolean isCorrectPassword(String oldPassword ,CustomerPassword customerPassword){
        Boolean checkPass = false;
        if (oldPassword.equals(customerPassword.getOldPassword())){
            if (customerPassword.getNewPassword().equals(customerPassword.getAgainNewPassword())){
                checkPass = true;
            } else {
                checkPass = false;
            }
        } else {
            checkPass = false;
        }
        return checkPass;
    }
}