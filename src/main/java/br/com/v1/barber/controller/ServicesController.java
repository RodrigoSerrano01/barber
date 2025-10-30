package br.com.v1.barber.controller;
import br.com.v1.barber.domain.Services;
import br.com.v1.barber.dto.servicesDto.ServicesCreationDto;
import br.com.v1.barber.dto.servicesDto.ServicesDto;
import br.com.v1.barber.dto.servicesDto.ServicesUpdateDto;
import br.com.v1.barber.service.impl.ServicesServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServicesController extends RootController{


    private final ServicesServiceImpl serviceImpl;

    @GetMapping(path ="/services")
    @ResponseStatus(HttpStatus.OK)
    public List<ServicesDto> getAllServices(){
        return serviceImpl.getAllServices();
    }

    @PostMapping(path ="services/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ServicesDto createServices (@RequestBody ServicesCreationDto servicesCreationDto){
        return serviceImpl.createServices(servicesCreationDto);
    }

    @DeleteMapping(path ="services/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServices (@PathVariable String id){
        serviceImpl.deleteServices(id);
    }

    @PutMapping(path = "services/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ServicesDto updateServices(@PathVariable String id, @Valid @RequestBody ServicesUpdateDto servicesUpdateDto) {
        return serviceImpl.updateServices(id, servicesUpdateDto);
    }

}
