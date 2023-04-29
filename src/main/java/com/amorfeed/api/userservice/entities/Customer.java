package com.amorfeed.api.userservice.entities;
import lombok.*;
import javax.persistence.*;
import java.awt.*;
import java.sql.Blob;
import java.util.Base64;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")

public class Customer {
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

}
