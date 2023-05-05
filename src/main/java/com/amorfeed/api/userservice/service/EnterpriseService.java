package com.amorfeed.api.userservice.service;

import com.amorfeed.api.userservice.entity.Enterprise;

import java.util.List;
import java.util.Optional;

public interface EnterpriseService {
    Optional<Enterprise> update(Long id, Enterprise request);

    List<Enterprise> getListbyScore(int Score);
}
