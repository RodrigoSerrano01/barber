package br.com.v1.barber.controller;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.dto.clientDto.ClientCreationDto;
import br.com.v1.barber.dto.clientDto.ClientDto;
import br.com.v1.barber.dto.clientDto.ClientUpdateDto;
import br.com.v1.barber.service.impl.ClientServerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientController extends RootController{
    private final ClientServerImpl service;


    @GetMapping(path ="/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients(){
        return service.getAllClients();
    }

    @PostMapping(path ="/clients/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto createClient (@RequestBody ClientCreationDto client){
        return service.createClient(client);
    }

    @DeleteMapping(path ="/clients/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteClient (@PathVariable String id){
        service.deleteClient(id);
    }

    @PutMapping("clients/{id}/update")
    public ClientDto updadteClient (@PathVariable String id, @RequestBody ClientUpdateDto updatedClient){
        return service.updateClient(id, updatedClient);
    }

}
