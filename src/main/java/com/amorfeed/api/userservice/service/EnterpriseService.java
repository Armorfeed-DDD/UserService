package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.Customer;
import com.amorfeed.api.userservice.entity.Enterprise;

import java.util.List;
public interface EnterpriseService {

    public List<Enterprise> findEnterpriseAll();
    public Enterprise createEnterprise(Enterprise enterprise);
    public Enterprise updateEnterprise(Enterprise enterprise);
    public Enterprise deleteEnterprise(Enterprise customer);
    public Enterprise updateEmail(String currentEmail,String newEmail);
    public Enterprise updatePassword(String email, String currentPassword, String newPassword);
    public  Enterprise getEnterprise(Long id);
}
