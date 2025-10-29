package br.com.v1.barber.repository;

import br.com.v1.barber.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
}
