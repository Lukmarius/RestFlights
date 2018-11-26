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

@BasePathAwareController
@RequestMapping(value = "api/passengers/")
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

    @ResponseBody
    @GetMapping("{id}")
    public Resource<Passenger> getPassenger(@PathVariable(name = "id") long id){
        Passenger passenger = passengerService.findPassengerByPassengerId(id);
        if (passenger == null){
            throw new ResourceNotFoundException();
        }

        return resourceAssembler.toResource(passenger);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void deletePassenger(@PathVariable(name = "id") long id){
        Passenger passenger = passengerService.findPassengerByPassengerId(id);
        if (passenger == null){
            throw new ResourceNotFoundException();
        }

        passengerService.deletePassengerByPassengerId(id);
    }

//    @PostMapping(path = "/", consumes = "application/json")
//    public Passenger addPassenger(@RequestBody Passenger reqPassenger){
//        passengerService.save(reqPassenger);
//        return reqPassenger;
//    }

//    @PutMapping(path = "/{id}", consumes = "application/json")
//    public Passenger updatePassenger(@PathVariable(name = "id") long id,
//                                     @RequestBody Passenger reqPassenger){
//        Passenger updatingPassenger = passengerService.findPassengerByPassengerId(id);
//        if (updatingPassenger == null){
//            throw new ResourceNotFoundException();
//        }
//        updatingPassenger.setFirstname(reqPassenger.getFirstname());
//        updatingPassenger.setLastname(reqPassenger.getLastname());
//
//        passengerService.save(updatingPassenger);
//
//        return updatingPassenger;
//    }


//    @GetMapping("")
//    public ResponseEntity<PagedResources<Passenger>> AllPassengers(Pageable pageable, PagedResourcesAssembler assembler) {
//        Page<Passenger> passengers = passengerService.findAllOnPage(pageable);
//        PagedResources < Passenger > pagedResources = assembler.toResource(passengers,
//                linkTo(PassengersRestController.class).slash("/").withSelfRel());
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.add("Link", createLinkHeader(pagedResources));
//        return new ResponseEntity < > (assembler.toResource(passengers,
//                linkTo(PassengersRestController.class).slash("/").withSelfRel()), responseHeaders, HttpStatus.OK);
//    }
//
//    private String createLinkHeader(PagedResources < Passenger > pr) {
//        final StringBuilder linkHeader = new StringBuilder();
//        linkHeader.append(buildLinkHeader(pr.getLinks("first").get(0).getHref(), "first"));
//        try{
//            linkHeader.append(", ");
//            linkHeader.append(buildLinkHeader(pr.getLinks("next").get(0).getHref(), "next"));
//        }catch (IndexOutOfBoundsException e){
//
//        }finally {
//            return linkHeader.toString();
//        }
//    }
//
//    private String buildLinkHeader(final String uri, final String rel) {
//        return "<" + uri + ">; rel=\"" + rel + "\"";
//    }
}
