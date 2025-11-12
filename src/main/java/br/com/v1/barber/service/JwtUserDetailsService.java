package br.com.v1.barber.service;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));
        return new org.springframework.security.core.userdetails.User(
                client.getEmail(),
                client.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))
        );
    }
}
