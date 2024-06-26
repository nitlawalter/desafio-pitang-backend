package br.com.tce.desafiopitangbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private String login;
    private String password;
    private String phone;
    private String createdAt;
    private String lastLogin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Car> cars;

}
