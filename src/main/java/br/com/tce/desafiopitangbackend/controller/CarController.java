package br.com.tce.desafiopitangbackend.controller;

import br.com.tce.desafiopitangbackend.dto.CarRequestDTO;
import br.com.tce.desafiopitangbackend.dto.CarResponseDTO;
import br.com.tce.desafiopitangbackend.exceptions.ErrorResponse;
import br.com.tce.desafiopitangbackend.model.Car;
import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.service.CarService;
import br.com.tce.desafiopitangbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@Validated
public class CarController {

    private final CarService carService;
    private final UserService userService;

    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCars(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userService.findByLogin(userDetails.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorResponse("Unauthorized", 401));
        }
        List<Car> cars = carService.findAllByUser(user.get());

        List<CarResponseDTO> carsResponse = CarResponseDTO.toCarResponseDTO(cars);
        return ResponseEntity.ok(carsResponse);
    }

    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody CarRequestDTO carDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userService.findByLogin(userDetails.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorResponse("Unauthorized", 401));
        }
        if (carService.existsByLicensePlate(carDTO.getLicensePlate())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("License plate already exists", 400));
        }
        Car car = new Car();
        car.setCarYear(carDTO.getCarYear());
        car.setModel(carDTO.getModel());
        car.setColor(carDTO.getColor());
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setUser(user.get());
        carService.save(car);

        CarResponseDTO carResponseDTO = CarResponseDTO.fromCar(car);

        return ResponseEntity.ok(carResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userService.findByLogin(userDetails.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorResponse("Unauthorized", 401));
        }
        Optional<Car> car = carService.findByIdAndUser(id, user.get().getId());
        if (car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @Valid @RequestBody CarRequestDTO carDTO, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userService.findByLogin(userDetails.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorResponse("Unauthorized", 401));
        }
        Optional<Car> car = carService.findByIdAndUser(id, user.get().getId());
        if (car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        car.get().setCarYear(carDTO.getCarYear());
        car.get().setModel(carDTO.getModel());
        car.get().setColor(carDTO.getColor());
        if (!car.get().getLicensePlate().equals(carDTO.getLicensePlate()) && carService.existsByLicensePlate(carDTO.getLicensePlate())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("License plate already exists", 400));
        }
        car.get().setLicensePlate(carDTO.getLicensePlate());
        carService.save(car.get());
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = userService.findByLogin(userDetails.getUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(401).body(new ErrorResponse("Unauthorized", 401));
        }
        Optional<Car> car = carService.findByIdAndUser(id, user.get().getId());
        if (car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        carService.delete(car.get());
        return ResponseEntity.noContent().build();
    }
}
