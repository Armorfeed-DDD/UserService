package com.amorfeed.api.userservice.service.impl;

import com.amorfeed.api.userservice.entity.Customer;
import com.amorfeed.api.userservice.repository.CustomerRepository;
import com.amorfeed.api.userservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }


    @Override
    public Customer createCustomer(Customer customer) {

        Customer customerDB = customerRepository.findByName ( customer.getName () );
        if (customerDB != null){
            return  customerDB;
        }

        customerDB = customerRepository.save ( customer );
        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null){
            return  null;
        }
        customerDB.setName(customer.getName());
        customerDB.setPhone(customer.getPhone());
        customerDB.setEmail(customer.getEmail());
        customerDB.setImage(customer.getImage());
        customerDB.setRuc(customer.getRuc());
        customerDB.setDescription(customer.getDescription());
        customerDB.setSubscription(customer.getSubscription());
        customerDB.setPassword(customer.getPassword());

        return  customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB ==null){
            return  null;
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateEmail(String currentEmail,String newEmail){
        Customer customer=customerRepository.findByEmail(currentEmail);
        if(customer==null){
            return null;
        }
        customer.setEmail(newEmail);
        return customerRepository.save(customer);
    }

    public Customer updatePassword(String email,String currentPassword,String newPassword){
        Customer customer=customerRepository.findByEmail(email);
        if(customer==null){
            return null;
        }
        if(!customer.getPassword().equals(currentPassword)){
            return null;
        }
        customer.setPassword(newPassword);
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return  customerRepository.findById(id).orElse(null);
    }

}
