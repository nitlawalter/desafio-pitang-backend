package br.com.tce.desafiopitangbackend.service;

import br.com.tce.desafiopitangbackend.model.Car;
import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public Car findCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }


    public Optional<Car> findByIdAndUser(Long idCar, Long idUser) {
        return carRepository.findByIdAndUserId(idCar, idUser);
    }

    public boolean existsByLicensePlate(String licensePlate) {
        return carRepository.existsByLicensePlate(licensePlate);
    }

    public void delete(Car car) {
        carRepository.deleteById(car.getId());
    }

    public List<Car> findAllByUser(User user) {
        return carRepository.findAllByUser(user);
    }
}
