package com.amorfeed.api.userservice.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enterprises")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private byte[] image;
    private String ruc;
    private String phone;
    private String description;
    @Column(unique = true)
    private int score;
}
