package br.com.tce.desafiopitangbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_year")
    private int carYear;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    private String model;
    private String color;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
