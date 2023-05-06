package com.amorfeed.api.userservice.entity;


import com.amorfeed.api.userservice.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@With
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    @Email
    private String email;

    @Column(nullable=false, unique=true)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles=new HashSet<>();
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = false;
    }
}
