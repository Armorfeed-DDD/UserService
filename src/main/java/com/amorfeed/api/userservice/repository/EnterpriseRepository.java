package com.amorfeed.api.userservice.repository;

import com.amorfeed.api.userservice.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Enterprise findByRUC(String RUC);
    List<Enterprise> findByScore(int Score);
}
