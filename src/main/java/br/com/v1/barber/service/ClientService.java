package br.com.v1.barber.service;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface ClientService {

    public Client findClientById (String id);
    public List<ClientDto> getAllClients();
    public ClientDto createClient(ClientCreationDto clientCreationDto);
    public void deleteClient (String id);
    public ClientDto updateClient (String id, ClientUpdateDto clientUpdateDto);

}
