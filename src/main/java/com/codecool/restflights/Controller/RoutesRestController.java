package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Route;
import com.codecool.restflights.Service.Intarfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RequestMapping("api/routes")
@RestController
public class RoutesRestController {

    private EntityLinks entityLinks;
    private RouteService routeService;

    @Autowired
    public RoutesRestController(EntityLinks entityLinks, RouteService routeService, HttpServletRequest request) {
        this.entityLinks = entityLinks;
        this.routeService = routeService;
    }

    @GetMapping("/{id}")
    public Route getRoute(@PathVariable long id){

        Route route = routeService.findRouteByRelationId(id);
        if (route == null){
            throw new ResourceNotFoundException();
        }

        // creating links into json
        Link link1 = entityLinks.linkToSingleResource(Airport.class, route.getFromAirport());
        Link link2 = entityLinks.linkToSingleResource(Airport.class, route.getDestinationAirport());
        Link link3 = entityLinks.linkToSingleResource(Route.class, id);
        route.add(Arrays.asList(link3, link1, link2));
        return route;
    }

    @GetMapping(value = "", params = {"page", "size"})
    public List<Route> getAllRoutes(@RequestParam( "page" ) int page, @RequestParam( "size" ) int size,
                                    UriComponentsBuilder uriBuilder, HttpServletResponse response ){

        Page <Route> resultPage = routeService.findAll(page, size);
        if( page > resultPage.getTotalPages() ) {
            throw new ResourceNotFoundException();
        }
//        eventPublisher.publishEvent( new PaginatedResultsRetrievedEvent< Foo >
//                ( Foo.class, uriBuilder, response, page, resultPage.getTotalPages(), size ) );
//
        for (Route route : resultPage){
            Link link1 = entityLinks.linkToSingleResource(Airport.class, route.getFromAirport());
            Link link2 = entityLinks.linkToSingleResource(Airport.class, route.getDestinationAirport());
            route.add(Arrays.asList(link1, link2));
        }
        return resultPage.getContent();
    }
}
