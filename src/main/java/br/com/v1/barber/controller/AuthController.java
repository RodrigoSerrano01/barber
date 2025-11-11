package br.com.v1.barber.controller;

import br.com.v1.barber.domain.User;
<<<<<<< HEAD
import br.com.v1.barber.dto.authDto.AuthRequest;
import br.com.v1.barber.dto.authDto.AuthResponse;
=======
>>>>>>> e159906 (password encoder)
import br.com.v1.barber.repository.UserRepository;
import br.com.v1.barber.service.impl.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtServiceImpl jtwServiceImpl;
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()) .orElseThrow(() -> new RuntimeException("User not found"));;

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jtwServiceImpl.generateToken(new HashMap<>(), user.getEmail());
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = jtwServiceImpl.register(user);
        return ResponseEntity.ok(createdUser);
    }

}
