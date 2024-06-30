package br.com.tce.desafiopitangbackend.dto;

import br.com.tce.desafiopitangbackend.model.Car;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO {

    private int carYear;
    private String licensePlate;
    private String model;
    private String color;

    public static List<CarResponseDTO> toCarResponseDTO(List<Car> cars) {
        return cars.stream().map(car -> CarResponseDTO.builder()
                .carYear(car.getCarYear())
                .licensePlate(car.getLicensePlate())
                .model(car.getModel())
                .color(car.getColor())
                .build()).toList();
    }

    public static CarResponseDTO fromCar(Car car) {
        return CarResponseDTO.builder()
                .carYear(car.getCarYear())
                .licensePlate(car.getLicensePlate())
                .model(car.getModel())
                .color(car.getColor())
                .build();
    }
}
