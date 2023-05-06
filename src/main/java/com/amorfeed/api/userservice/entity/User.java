package com.amorfeed.api.userservice.entity;


import com.amorfeed.api.userservice.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;

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
    @Column(nullable=false)

    private String name;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false, unique=true)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = false;
    }
}
