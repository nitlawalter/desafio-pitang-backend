package br.com.tce.desafiopitangbackend.controller;

import br.com.tce.desafiopitangbackend.exceptions.ErrorResponse;
import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.service.UserService;
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

    private PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email already exists", 400));
        }
        if (userService.findByLogin(user.getLogin())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Login already exists", 400));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setBirthday(userDetails.getBirthday());
        user.setLogin(userDetails.getLogin());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setPhone(userDetails.getPhone());
        user.setCars(userDetails.getCars());
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
