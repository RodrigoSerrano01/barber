package br.com.v1.barber.repository;

import br.com.v1.barber.domain.User;
import br.com.v1.barber.dto.userDto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);
}