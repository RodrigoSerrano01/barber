package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Employee;
import br.com.v1.barber.domain.User;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.employeeDto.EmployeeDto;
import br.com.v1.barber.dto.userDto.UserDto;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.repository.EmployeeRepository;
import br.com.v1.barber.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;


@Slf4j
@Service
@RequiredArgsConstructor

public class JwtServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);



    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        log.info("teste {} ", user.getUserRole());
        userRepository.save(user);
        log.info("Sucess registered {} ",user.getName());
    }


    public String generateToken(Map<String, Object> extraClaims, String subject) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateTokenToClient(ClientDto clientDetails) {
        return generateToken(new HashMap<>(), clientDetails.getEmail());
    }
    public String generateTokenToEmployee(EmployeeDto employeeDto) {
        return generateToken(new HashMap<>(), employeeDto.getEmail());
    }
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Primeiro tenta achar como cliente
        Optional<Client> clientOpt = clientRepository.findByEmailIgnoreCase(email);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    client.getEmail(),
                    client.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))
            );
        }

        // Se não achou como cliente, tenta achar como employee
        Optional<Employee> employeeOpt = employeeRepository.findByEmailIgnoreCase(email);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    employee.getEmail(),
                    employee.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))
            );
        }

        // Se não achou nenhum dos dois
        throw new UsernameNotFoundException("Usuário não encontrado");
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Client client = clientRepository.findByEmailIgnoreCase(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));
//        return new org.springframework.security.core.userdetails.User(
//                client.getEmail(),
//                client.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))
//        );
//    }

//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                getAuthorities(user));
//    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // Aqui você pode mapear roles ou perfis do usuário
        // Exemplo simples: todos usuários têm ROLE_USER
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENT"));
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey()) // seu método que retorna a Key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public boolean isTokenValid(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

}
