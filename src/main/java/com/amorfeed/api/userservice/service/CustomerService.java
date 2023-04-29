package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> findCustomerAll();
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public Customer updateEmail(String currentEmail,String newEmail);
    public Customer updatePassword(String email,String currentPassword,String newPassword);
    public  Customer getCustomer(Long id);
}
