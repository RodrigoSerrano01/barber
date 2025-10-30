package br.com.v1.barber.controller;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.service.impl.ServicesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServicesController extends RootController{


    private final ServicesServiceImpl service;

    @GetMapping(path ="/services")
    @ResponseStatus(HttpStatus.OK)
    public List<Services> getAllServices(){
        return service.getAllServices();
    }

    @PostMapping(path ="services/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Services createServices (@RequestBody Services services){
        return service.createServices(services);
    }

    @DeleteMapping(path ="services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServices (@PathVariable String id){
        service.deleteServices(id);
    }


}
