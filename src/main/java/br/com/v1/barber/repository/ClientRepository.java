package br.com.v1.barber.repository;

import br.com.v1.barber.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findTopByNameEqualsIgnoreCase(String name);
    Optional<Client> findByEmailIgnoreCase(String email);
}
