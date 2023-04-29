package com.amorfeed.api.userservice.entity;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customers")

public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private byte[] image;
    private String ruc;
    private String phone;
    private String description;
    @Column(unique = true)
    private String email;
    private String password;
    private String subscription;


}
