package com.amorfeed.api.userservice.entity;

import com.amorfeed.api.userservice.entity.Enum.Roles;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private Roles name;


    public Role(Roles name) {
        this.name = name;
    }
}
