package br.com.tce.desafiopitangbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequestDTO {

    @NotNull(message = "Car year is mandatory")
    private int carYear;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Color is mandatory")
    private String color;

    @NotBlank(message = "License plate is mandatory")
    private String licensePlate;
}
