package br.com.v1.barber.service.impl;

import br.com.v1.barber.domain.Client;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.dto.mapping.ClientMapper;
import br.com.v1.barber.exception.handler.ClientAlreadyExistsException;
import br.com.v1.barber.exception.handler.UserNotFoundException;
import br.com.v1.barber.repository.ClientRepository;
import br.com.v1.barber.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.v1.barber.enumerator.Error.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository repository;

    public Client findClientById(String id){
        log.info("Searching client by id {}",id);
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NO_CLIENT_FOUND_BY_ID.getErrorDescription()));
    }

    @Override
    public List<ClientDto> getAllClients(){
        log.info("Searching for clients");
        final List<Client> clients = repository.findAll();
        if(clients.isEmpty()){
            log.info((NO_CLIENT_FOUND.getErrorDescription()));
            throw new UserNotFoundException(NO_CLIENT_FOUND.getErrorDescription());
        }
        return clientMapper.clientListToClientListDto(clients);
    }
    @Override
    public ClientDto createClient(ClientCreationDto clientCreationDto) {
        log.info("Searching client by name {} ",clientCreationDto.getName());

        final Optional<Client> clientSearching = repository.findTopByNameEqualsIgnoreCase(clientCreationDto.getName());
        if(clientSearching.isPresent()){
            log.info(CLIENT_ALREADY_REGISTERED.getErrorDescription());
            throw new ClientAlreadyExistsException(CLIENT_ALREADY_REGISTERED.getErrorDescription());
        }
        final Client client = clientMapper.clientCreationDtoToClient(clientCreationDto);
        repository.save(client);
        log.info("Sucess registered{} ",client.getName());
        return clientMapper.clientToClientDto(client);
    }
    @Override
    public void deleteClient (String id){
        final Client client = this.findClientById(id);
        repository.deleteById(id);
        log.info("Client {} deleted",client.getName());
    }

    @Override
    public ClientDto updateClient(String id, ClientUpdateDto update) {
        final Client existingClient = this.findClientById(id);
        clientMapper.clientUpdateDtoToClient(update, existingClient);
        repository.save(existingClient);
        log.info("Success updated {}",existingClient.getName());
        return clientMapper.clientToClientDto(existingClient);
    }
}
