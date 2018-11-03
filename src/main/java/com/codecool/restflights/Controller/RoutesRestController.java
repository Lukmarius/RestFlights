package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Route;
import com.codecool.restflights.Service.RouteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
        // creating links into json
        Link link1 = entityLinks.linkToSingleResource(Airport.class, route.getFromAirport());
        Link link2 = entityLinks.linkToSingleResource(Airport.class, route.getDestinationAirport());
        Link link3 = entityLinks.linkToSingleResource(Route.class, id);
        route.add(Arrays.asList(link3, link1, link2));
        return route;
    }

    @GetMapping("")
    public List<Route> getAllRoutes(){

        List<Route> list = routeService.findAll();
        for (Route route : list){
            Link link1 = entityLinks.linkToSingleResource(Airport.class, route.getFromAirport());
            Link link2 = entityLinks.linkToSingleResource(Airport.class, route.getDestinationAirport());
            route.add(Arrays.asList(link1, link2));
        }
        return list;
    }
}
