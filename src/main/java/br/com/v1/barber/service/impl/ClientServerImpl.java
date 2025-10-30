package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.dto.mapping.ClientMapper;
import br.com.v1.barber.exception.handler.UserNotFoundException;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.v1.barber.enumerator.Error.NO_CLIENT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class ClientServerImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository repository;

    public Client findClientById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NO_CLIENT_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public List<ClientDto> getAllClients(){
        final List<Client> clients = repository.findAll();
        return clientMapper.clientListToClientListDto(clients);
    }
    @Override
    public ClientDto createClient(ClientCreationDto clientCreationDto) {
        final Client client = clientMapper.clientCreationDtoToClient(clientCreationDto);
        repository.save(client);
        return clientMapper.clientToClientDto(client);
    }
    @Override
    public void deleteClient (String id){
        repository.deleteById(id);
    }

    @Override
    public ClientDto updateClient(String id, ClientUpdateDto update) {
        final Client existingClient = this.findClientById(id);
        clientMapper.clientUpdateDtoToClient(update, existingClient);
        repository.save(existingClient);
        return clientMapper.clientToClientDto(existingClient);


    }
}
