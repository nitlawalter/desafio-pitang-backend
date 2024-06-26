package br.com.tce.desafiopitangbackend.service;

import br.com.tce.desafiopitangbackend.model.Car;
import br.com.tce.desafiopitangbackend.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car findCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

}
