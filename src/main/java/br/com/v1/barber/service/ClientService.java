package br.com.v1.barber.service;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getAllClients(){
        return repository.findAll();
    }
    public Client createClient(Client client) {
        return repository.save(client);
    }

    public void deleteClient (String id){
        repository.deleteById(id);
    }
}
