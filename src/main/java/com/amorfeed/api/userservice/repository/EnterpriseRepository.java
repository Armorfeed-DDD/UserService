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
    @Query(value = "SELECT et from Enterprise et where et.ruc like '%:ruc%'")
    Enterprise findByRUC(@Param("ruc") String ruc);
    @Query(value = "SELECT * from Enterprise et where et.score = :score", nativeQuery = true)
    List<Enterprise> findByScore(@Param("score") int score);
}
