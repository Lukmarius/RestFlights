package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Passenger;
import com.codecool.restflights.Service.Intarfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/passengers")
@RestController
public class PassengersRestController {

    private PassengerService passengerService;

//    Now, we have to implement only ActivePassengerService (for safe deleting)
    @Autowired
    public PassengersRestController(@Qualifier("ActivePassengerServiceImpl") PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePassenger(@PathVariable(name = "id") long id){
        Passenger passenger = passengerService.findPassengerByPassengerId(id);
        if (passenger == null){
            throw new ResourceNotFoundException();
        }

        passengerService.deletePassengerByPassengerId(id);
    }

    @GetMapping("/{id}")
    public Passenger getPassenger(@PathVariable(name = "id") long id){
        Passenger passenger = passengerService.findPassengerByPassengerId(id);
        if (passenger == null){
            throw new ResourceNotFoundException();
        }

        return passengerService.findPassengerByPassengerId(id);
    }

    @GetMapping("")
    public List<Passenger> getAllPassengers(){

        return passengerService.findAll();
    }
}
