package br.com.tce.desafiopitangbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

        @NotBlank(message = "First name is mandatory")
        private String firstName;

        @NotBlank(message = "Last name is mandatory")
        private String lastName;

        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        private String email;

        private LocalDate birthday;

        @NotBlank(message = "Login is mandatory")
        private String login;

        @NotBlank(message = "Password is mandatory")
        private String password;

        private String phone;

        private List<CarRequestDTO> cars;

}
