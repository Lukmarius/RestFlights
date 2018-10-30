package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Passenger;
import com.codecool.restflights.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("2api")
@RestController
public class RestApiController {

    private PassengerService passengerService;

    @Autowired
    public RestApiController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @DeleteMapping("/passengers/{id}")
    @Transactional
    public void deletePassenger(@PathVariable(name = "id") long id){
        System.out.println("_______________________DELETED_____!!!!!_________"+id);
        passengerService.deletePassengerByPassengerId(id);
    }

    @GetMapping("/passengers/{id}")
    public Passenger getPassenger(@PathVariable(name = "id") long id){
        System.out.println("_______________________GET_____!!!!!_________"+id);
        return passengerService.findPassengerByPassengerId(id);
    }

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers(){
        System.out.println("_______________________GET_____!!!!!_________");
        return passengerService.findAll();
    }
}
