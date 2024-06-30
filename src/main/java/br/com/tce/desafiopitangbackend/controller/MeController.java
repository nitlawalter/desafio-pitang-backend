package br.com.tce.desafiopitangbackend.controller;

import br.com.tce.desafiopitangbackend.dto.UserResponseDTO;
import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/me")
@Validated
public class MeController {

    private final UserService userService;

    public MeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        Optional<User> user = userService.findByEmail(userDetails.getUsername());

        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.ok(
                UserResponseDTO.builder()
                        .login(user.get().getLogin())
                        .email(user.get().getEmail())
                        .firstName(user.get().getFirstName())
                        .lastName(user.get().getLastName())
                        .phone(user.get().getPhone())
                        .birthday(user.get().getBirthday())
                        .cars(user.get().getCars())
                        .createdAt(user.get().getCreatedAt().toString())
                        .lastLogin(user.get().getLastLogin())
                        .build());
    }
}
