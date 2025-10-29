package br.com.v1.barber.controller;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final  ClientService service;


    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients(){
        return service.getAllClients();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient (@RequestBody Client client){
        return service.createClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteClient (@PathVariable String id){
        service.deleteClient(id);
    }

}
