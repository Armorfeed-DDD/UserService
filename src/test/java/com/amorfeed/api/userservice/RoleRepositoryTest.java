package com.amorfeed.api.userservice;

import com.amorfeed.api.userservice.entity.Enum.Roles;
import com.amorfeed.api.userservice.entity.Role;
import com.amorfeed.api.userservice.repository.RoleRepository;
import com.amorfeed.api.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;
    @Test
    public void testCreateRoles(){
        Role admin= new Role(Roles.ADMIN);
        Role customer= new Role(Roles.CUSTOMER);
        Role enterprise= new Role(Roles.ENTERPRISE);
        roleRepository.saveAll(List.of(admin,customer,enterprise));

    }

}
