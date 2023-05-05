package com.amorfeed.api.userservice.entity;


import com.amorfeed.api.userservice.shared.model.AuditModel;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@With
@AllArgsConstructor
@Table(name = "user")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)

    private String name;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false, unique=true)
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
