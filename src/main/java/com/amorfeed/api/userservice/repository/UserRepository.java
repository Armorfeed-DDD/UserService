package com.amorfeed.api.userservice.repository;

import com.amorfeed.api.userservice.entity.User;
import com.amorfeed.api.userservice.entity.Enum.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u JOIN u.roles ur WHERE u.id = :userId AND ur.name = :role")
    Optional<User> findUserIdByIdAndRole(@Param("userId") Long userId, @Param("role") Roles role);
}
