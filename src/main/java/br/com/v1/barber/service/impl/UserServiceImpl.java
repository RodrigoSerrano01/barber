package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.User;
import br.com.v1.barber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository repository;


    public User findByEmail(String email) {
        return repository.findByEmail(email);

    }
}
