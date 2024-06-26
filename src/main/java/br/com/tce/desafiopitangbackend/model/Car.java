package br.com.tce.desafiopitangbackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String licensePlate;
    private String model;
    private String color;

}
