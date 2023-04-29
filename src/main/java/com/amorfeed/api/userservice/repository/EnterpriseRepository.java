package com.amorfeed.api.userservice.repository;

import com.amorfeed.api.userservice.entity.Customer;
import com.amorfeed.api.userservice.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface EnterpriseRepository extends JpaRepository<Enterprise,Long>{
    public Enterprise findByName(String name);
    public Enterprise findByEmail(String email);
}
