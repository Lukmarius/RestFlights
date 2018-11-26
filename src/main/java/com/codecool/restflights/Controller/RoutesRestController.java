package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Route;
import com.codecool.restflights.Service.Implementations.PassengerResourceAssembler;
import com.codecool.restflights.Service.Implementations.RouteResourceAssembler;
import com.codecool.restflights.Service.Interfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RequestMapping("api/routes")
@BasePathAwareController
public class RoutesRestController {

    private RouteService routeService;
    private RouteResourceAssembler resourceAssembler;
    private EntityLinks entityLinks;

    @Autowired
    public RoutesRestController(RouteService routeService,
                                RouteResourceAssembler resourceAssembler,
                                EntityLinks entityLinks) {
        this.routeService = routeService;
        this.resourceAssembler = resourceAssembler;
        this.entityLinks = entityLinks;
    }

    @ResponseBody
    @GetMapping("")
    public PagedResources<Page <Resource<Route>>> getAllRoutes(Pageable pageable, PagedResourcesAssembler pagedAssembler){

        Page <Resource<Route>> routes = routeService.findAllOnPage(pageable)
                .map(resourceAssembler::toResource);

        PagedResources<Page <Resource<Route>>> pagedResources = pagedAssembler.toResource(routes,
                linkTo(RoutesRestController.class).slash("/").withSelfRel());

        return pagedResources;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Resource<Route> getRoute(@PathVariable long id){
        Route route = routeService.findRouteByRelationId(id);
        if (route == null){
            throw new ResourceNotFoundException();
        }
        return resourceAssembler.toResource(route);
    }
}
