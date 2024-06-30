package br.com.tce.desafiopitangbackend.dto;

import br.com.tce.desafiopitangbackend.model.Car;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String login;
    private String phone;
    private List<Car> cars;
    private String createdAt;
    private String lastLogin;
}
