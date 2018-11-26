package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Route;
import com.codecool.restflights.Service.Implementations.RouteResourceAssembler;
import com.codecool.restflights.Service.Interfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RequestMapping("api/routes/")
@BasePathAwareController
public class RoutesRestController {

    private RouteService routeService;
    private RouteResourceAssembler resourceAssembler;

    @Autowired
    public RoutesRestController(RouteService routeService, RouteResourceAssembler resourceAssembler) {
        this.routeService = routeService;
        this.resourceAssembler = resourceAssembler;
    }

    @ResponseBody
    @GetMapping("{id}")
    public Resource<Route> getRoute(@PathVariable long id){
        Route route = routeService.findRouteByRelationId(id);
        if (route == null){
            throw new ResourceNotFoundException();
        }
        return resourceAssembler.toResource(route);
    }
}
