package br.com.tce.desafiopitangbackend.repository;

import br.com.tce.desafiopitangbackend.model.Car;
import br.com.tce.desafiopitangbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByLicensePlate(String licensePlate);

    boolean existsByLicensePlate(String licensePlate);

    Optional<Car> findByIdAndUserId(Long idCar, Long idUser);

    List<Car> findAllByUser(User user);
}
