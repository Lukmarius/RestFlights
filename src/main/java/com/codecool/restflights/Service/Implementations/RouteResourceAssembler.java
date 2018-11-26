package com.codecool.restflights.Service.Implementations;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class RouteResourceAssembler implements ResourceAssembler<Route, Resource<Route>> {

    private final EntityLinks entityLinks;

    @Autowired
    public RouteResourceAssembler(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public Resource<Route> toResource(Route route) {

        return new Resource<Route>(route,
                entityLinks.linkToSingleResource(Route.class, route.getRelationId()).withSelfRel(),
                entityLinks.linkToSingleResource(Airport.class, route.getFromAirport()).withRel("from"),
                entityLinks.linkToSingleResource(Airport.class, route.getDestinationAirport()).withRel("destination"));
    }
}
