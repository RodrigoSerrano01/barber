package br.com.v1.barber.repository;

import br.com.v1.barber.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;



public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmail (String email);
}
