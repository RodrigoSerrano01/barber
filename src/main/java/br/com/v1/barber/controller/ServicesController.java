package br.com.v1.barber.controller;


import br.com.v1.barber.domain.Client;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.service.ClientService;
import br.com.v1.barber.service.ServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {


    private final ServicesService service;


    public ServicesController(ServicesService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Services> getAllServices(){
        return service.getAllServices();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Services createServices (@RequestBody Services services){
        return service.createServices(services);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteServices (@PathVariable String id){
        service.deleteServices(id);
    }
}
