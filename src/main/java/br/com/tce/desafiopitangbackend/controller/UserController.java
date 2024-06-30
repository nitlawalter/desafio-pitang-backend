package br.com.tce.desafiopitangbackend.controller;

import br.com.tce.desafiopitangbackend.dto.UserRequestDTO;
import br.com.tce.desafiopitangbackend.exceptions.ErrorResponse;
import br.com.tce.desafiopitangbackend.mapper.UserMapper;
import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @PostMapping()
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO userDto) {
        if (userService.findByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email already exists", 400));
        }
        if (userService.findByLogin(userDto.getLogin())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Login already exists", 400));
        }
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userDto) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
