package com.codecool.restflights.Service.Implementations;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class PassengerResourceAssembler implements ResourceAssembler<Passenger, Resource<Passenger>> {

    @Autowired
    EntityLinks entityLinks;

    @Override
    public Resource<Passenger> toResource(Passenger passenger) {
        return new Resource<Passenger>(passenger,
                entityLinks.linkToSingleResource(Passenger.class, passenger.getPassengerId()).withSelfRel());
    }
}
