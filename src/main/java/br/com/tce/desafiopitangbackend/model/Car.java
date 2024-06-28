package br.com.tce.desafiopitangbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_year")
    private int carYear;

    private String licensePlate;
    private String model;
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
