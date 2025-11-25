package br.com.v1.barber.controller;
//
//import br.com.v1.barber.domain.User;
//
//import br.com.v1.barber.dto.authDto.AuthRequest;
//import br.com.v1.barber.dto.authDto.AuthResponse;
//
//import br.com.v1.barber.repository.UserRepository;
//import br.com.v1.barber.service.impl.JwtServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/auth")
//public class AuthController extends RootController{
//
//    private final UserRepository userRepository;
//    private final JwtServiceImpl jtwServiceImpl;
//    private final AuthenticationManager authManager;
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping(path = "/login")
//    @ResponseStatus(HttpStatus.OK)
//    public AuthResponse login(@RequestBody AuthRequest request) {
//        User user = userRepository.findByEmail(request.getEmail()) ;
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//        String token = jtwServiceImpl.generateToken(new HashMap<>(), user.getEmail());
//        return new AuthResponse(token);
//    }
//
//    @PostMapping(path = "/register")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<User> register(@RequestBody User user) {
//        User createdUser = jtwServiceImpl.register(user);
//        return ResponseEntity.ok(createdUser);
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request) {
//        authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//
//        UserDetails userDetails = jtwServiceImpl.loadUserByUsername(request.getEmail());
//        String token = jtwServiceImpl.generateToken(userDetails);
//
//        return ResponseEntity.ok(token);
//    }
//}

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.domain.User;
import br.com.v1.barber.dto.authDto.AuthRequest;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.employeeDto.EmployeeCreationDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.mapping.ClientMapper;
import br.com.v1.barber.dto.mapping.EmployeeMapper;
import br.com.v1.barber.dto.mapping.UserMapper;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.service.impl.ClientServiceImpl;
import br.com.v1.barber.service.impl.EmployeeServiceImpl;
import br.com.v1.barber.service.impl.JwtServiceImpl;
import br.com.v1.barber.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/barber-application/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtServiceImpl jwtService;
    private final ClientServiceImpl clientService;
    private final EmployeeServiceImpl employeeService;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;


    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final EmployeeMapper employeeMapper;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
//        System.out.println(request.getEmail());
//        User user = userService.findByEmail(request.getEmail());
//        System.out.println(user.getEmail());

//        System.out.println(client.getEmail());
switch (request.getUserRole()) {
    case ROLE_CLIENT -> {
        Client client = clientService.findByEmail(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            ClientDto clientDto = clientMapper.clientToClientDto(client);
            String token = jwtService.generateTokenToClient(clientDto);
            System.out.println(token);
            return ResponseEntity.ok(
                    Map.of(
                            "token", token,
                            "id", client.getId(),      // adiciona o ID
                            "name", client.getName(),   // opcional, se quiser mostrar na Home
                            "userRole", client.getUserRole().toString()
                    )
            );

        }
        throw new RuntimeException("Invalid credentials");
    }
    case ROLE_EMPLOYEE -> {
        Employee employee = employeeService.findByEmail(request.getEmail());
        if (passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
            String token = jwtService.generateTokenToEmployee(employeeDto);
            System.out.println(token);
            return ResponseEntity.ok(
                    Map.of(
                            "token", token,
                            "id", employee.getId(),      // adiciona o ID
                            "name", employee.getName(),   // opcional, se quiser mostrar na Home
                            "userRole", employee.getUserRole().toString()
                    )
            );


        }
        throw new RuntimeException("Invalid credentials");
    }
}




//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        client.getEmail(),
//                        client.getPassword()
//                )
//       );

//        return ResponseEntity.ok(Map.of("Erro",erro));
        throw new RuntimeException("ERROR");
    }



    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody JsonNode json) {
        String role = json.get("userRole").asText();

        switch (role){
            case "ROLE_CLIENT" -> {
                ClientCreationDto dto = objectMapper.convertValue(json, ClientCreationDto.class);
                System.out.println(dto.getName());
                clientService.createClient(dto);
                return ResponseEntity.ok("Cliente");

            }
            case "ROLE_EMPLOYEE" -> {
                EmployeeCreationDto dto = objectMapper.convertValue(json, EmployeeCreationDto.class);
                System.out.println(dto.getName());
                employeeService.createEmployee(dto);
                return ResponseEntity.ok("Employee");
            }
        }
        return ResponseEntity.ok("Erro");
    }
}