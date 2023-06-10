package com.amorfeed.api.userservice.repository;

import com.amorfeed.api.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.amorfeed.api.userservice.entity.Role;
import java.util.Set;
import java.util.List;





@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
    Optional<User> findByName(String name);
}
