package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> findCustomerAll();
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Customer customer);
    public  Customer getCustomer(Long id);
}
