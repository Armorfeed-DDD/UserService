package com.amorfeed.api.userservice.repository;

import com.amorfeed.api.userservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    public Customer findByName(String name);
    public List<Customer> findByLastName(String lastName);


}
