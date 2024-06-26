package br.com.tce.desafiopitangbackend.repository;

import br.com.tce.desafiopitangbackend.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByLicensePlate(String licensePlate);
}
