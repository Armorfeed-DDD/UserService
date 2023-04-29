package com.amorfeed.api.userservice.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Enterprise")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private byte[] image;
    private String ruc;
    private String phone;
    private String description;
    @Column(unique = true)
    private String email;
    private String password;
    private int score;
}
