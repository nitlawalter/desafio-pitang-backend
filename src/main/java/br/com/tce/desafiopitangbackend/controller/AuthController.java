package br.com.tce.desafiopitangbackend.controller;

import br.com.tce.desafiopitangbackend.dto.LoginRequestDTO;
import br.com.tce.desafiopitangbackend.exceptions.ErrorResponse;
import br.com.tce.desafiopitangbackend.model.User;
import br.com.tce.desafiopitangbackend.security.JwtResponse;
import br.com.tce.desafiopitangbackend.security.JwtTokenUtil;
import br.com.tce.desafiopitangbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.login(), loginRequest.password()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid login or password", 400));
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.login());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
