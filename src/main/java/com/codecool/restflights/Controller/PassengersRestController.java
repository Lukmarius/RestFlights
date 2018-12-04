package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Passenger;
import com.codecool.restflights.Service.Implementations.PassengerResourceAssembler;
import com.codecool.restflights.Service.Interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

//@RequestMapping(value = "api/") - not allowed here
@BasePathAwareController
public class PassengersRestController {

    private PassengerService passengerService;
    private PassengerResourceAssembler resourceAssembler;

//    Now, we have to implement only ActivePassengerService (for safe deleting)
    @Autowired
    public PassengersRestController(@Qualifier("ActivePassengerServiceImpl") PassengerService passengerService,
                                    PassengerResourceAssembler resourceAssembler) {
        this.passengerService = passengerService;
        this.resourceAssembler = resourceAssembler;
    }

    //    overring these methods. others are handling by default rest repository
    @ResponseBody
    @GetMapping("/passengers/{id}")
    public Resource<Passenger> getPassenger(@PathVariable(name = "id") long id){
        Passenger passenger = passengerService.findPassengerByPassengerId(id);
        if (passenger == null){
            throw new ResourceNotFoundException();
        }

        return resourceAssembler.toResource(passenger);
    }

    @ResponseBody
    @DeleteMapping("/passengers/{id}")
    public void deletePassenger(@PathVariable(name = "id") long id){
        Passenger passenger = passengerService.findPassengerByPassengerId(id);
        if (passenger == null){
            throw new ResourceNotFoundException();
        }

        passengerService.deletePassengerByPassengerId(id);
    }

}
