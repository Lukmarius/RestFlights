package com.codecool.restflights.Controller;

import com.codecool.restflights.Service.Interfaces.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@BasePathAwareController
public class AirportsRestController {

    private final AirportsService airportsService;

    @Autowired
    public AirportsRestController(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @DeleteMapping("api/airports/{id}")
    ResponseEntity<?> deleteAirport(@PathVariable String id) {
        try {
            airportsService.deleteById(id);
        }catch (NullPointerException e){
            System.out.println("not found");
            throw new ResourceNotFoundException();
        }
        return ResponseEntity.noContent().build();
    }
}
