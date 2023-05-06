package com.amorfeed.api.userservice.repository;

import com.amorfeed.api.userservice.entity.Enum.Roles;
import com.amorfeed.api.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
    boolean existsByName(Roles name);
}
