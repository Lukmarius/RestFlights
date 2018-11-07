package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Passenger;
import com.codecool.restflights.Model.Route;
import com.codecool.restflights.Service.Intarfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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

//    @GetMapping("")
//    public List<Passenger> getAllPassengers(){
//
//        return passengerService.findAll();
//    }

    @GetMapping("")
    public ResponseEntity<PagedResources<Passenger>> AllPassengers(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Passenger> passengers = passengerService.findAllOnPage(pageable);
        PagedResources < Passenger > pr = assembler.toResource(passengers,
                linkTo(PassengersRestController.class).slash("/").withSelfRel());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Link", createLinkHeader(pr));
        return new ResponseEntity < > (assembler.toResource(passengers,
                linkTo(PassengersRestController.class).slash("/").withSelfRel()), responseHeaders, HttpStatus.OK);
    }

    private String createLinkHeader(PagedResources < Passenger > pr) {
        final StringBuilder linkHeader = new StringBuilder();
        linkHeader.append(buildLinkHeader(pr.getLinks("first").get(0).getHref(), "first"));
        linkHeader.append(", ");
        linkHeader.append(buildLinkHeader(pr.getLinks("next").get(0).getHref(), "next"));
        return linkHeader.toString();
    }

    private String buildLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }
}
