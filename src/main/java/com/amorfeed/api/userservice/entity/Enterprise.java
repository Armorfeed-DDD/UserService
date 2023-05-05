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
    private Long id;

    @Column(nullable=false, unique=true)
    private byte[] image;
    @Column(nullable=false, unique=true)
    private String ruc;
    @Column(nullable=false, unique=true)
    private String phone;
    @Column(nullable=false, unique=true)
    private String description;
    @Column(unique = true)
    private int score;
}
