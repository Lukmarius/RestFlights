package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Passenger;
import com.codecool.restflights.Service.PassengerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/passengers")
@RestController
public class PassengersRestController {

    private PassengerService passengerService;

    @Autowired
    public PassengersRestController(@Qualifier("ActivePassengerServiceImpl") PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePassenger(@PathVariable(name = "id") long id){
        passengerService.deletePassengerByPassengerId(id);
    }

    @GetMapping("/{id}")
    public Passenger getPassenger(@PathVariable(name = "id") long id){

        return passengerService.findPassengerByPassengerId(id);
    }

    @GetMapping("")
    public List<Passenger> getAllPassengers(){

        return passengerService.findAll();
    }
}
