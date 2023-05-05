package com.amorfeed.api.userservice.repository;

import com.amorfeed.api.userservice.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    @Query(value = "SELECT et from enterprises et where et.RUC like '%:RUC%'")
    Enterprise findByRUC(@Param("RUC") String RUC);
    @Query(value = "SELECT * from enterprises et where et.Score like '%:Score'")
    List<Enterprise> findByScore(@Param("Score") int Score);
}
