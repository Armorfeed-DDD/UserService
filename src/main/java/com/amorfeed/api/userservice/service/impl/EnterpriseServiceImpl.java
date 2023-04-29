package com.amorfeed.api.userservice.service.impl;


import com.amorfeed.api.userservice.entity.Enterprise;
import com.amorfeed.api.userservice.repository.EnterpriseRepository;

import com.amorfeed.api.userservice.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseServiceImpl implements EnterpriseService{
    @Autowired
    EnterpriseRepository enterpriseRepository;
    public EnterpriseServiceImpl(EnterpriseRepository enterpriseRepository){
        this.enterpriseRepository=enterpriseRepository;
    }
    @Override
    public List<Enterprise> findEnterpriseAll() {
        return null;
    }

    @Override
    public Enterprise createEnterprise(Enterprise enterprise) {
        return null;
    }

    @Override
    public Enterprise updateEnterprise(Enterprise enterprise) {
        return null;
    }

    @Override
    public Enterprise deleteEnterprise(Enterprise customer) {
        return null;
    }

    @Override
    public Enterprise updateEmail(String currentEmail, String newEmail){
        Enterprise enterprise=enterpriseRepository.findByEmail(currentEmail);
        if(enterprise==null){
            return null;
        }
        enterprise.setEmail(newEmail);
        return enterpriseRepository.save(enterprise);
    }

    @Override
    public Enterprise updatePassword(String email, String currentPassword,String newPassword) {
        Enterprise enterprise=enterpriseRepository.findByEmail(email);
        if(enterprise==null){
            return null;
        }
        if(!enterprise.getPassword().equals(currentPassword)){
            return null;
        }
        enterprise.setPassword(newPassword);
        return enterpriseRepository.save(enterprise);
    }

    @Override
    public Enterprise getEnterprise(Long id) {
        return null;
    }
}
