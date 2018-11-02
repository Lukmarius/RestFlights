package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Passenger;
import com.codecool.restflights.Service.PassengerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("2api/passengers")
@RestController
public class PassengersRestController {

    private PassengerService passengerService;
    private static final Logger logger = Logger.getLogger(PassengersRestController.class);

    @Autowired
    public PassengersRestController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePassenger(@PathVariable(name = "id") long id){
        passengerService.deletePassengerByPassengerId(id);
        logger.info("DELETE 2api/passengers/"+id);
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
