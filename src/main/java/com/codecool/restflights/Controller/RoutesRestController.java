package com.codecool.restflights.Controller;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Route;
import com.codecool.restflights.Service.Intarfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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

//    @GetMapping(value = "", params = {"page", "size"})
//    public List<Route> getAllRoutes(@RequestParam( "page" ) int page, @RequestParam( "size" ) int size,
//                                    UriComponentsBuilder uriBuilder, HttpServletResponse response ){
//
//        Page <Route> resultPage = routeService.findAllOnPage(page, size);
//        if( page > resultPage.getTotalPages() ) {
//            throw new ResourceNotFoundException();
//        }
////        eventPublisher.publishEvent( new PaginatedResultsRetrievedEvent< Foo >
////                ( Foo.class, uriBuilder, response, page, resultPage.getTotalPages(), size ) );
////
//        for (Route route : resultPage){
//            Link link1 = entityLinks.linkToSingleResource(Airport.class, route.getFromAirport());
//            Link link2 = entityLinks.linkToSingleResource(Airport.class, route.getDestinationAirport());
//            route.add(Arrays.asList(link1, link2));
//        }
//        return resultPage.getContent();
//    }

    @GetMapping("")
    public ResponseEntity<PagedResources<Route>> AllRoutes(Pageable pageable, PagedResourcesAssembler assembler) {
        Page <Route> routes = routeService.findAllOnPage(pageable);
        addAirportLinksToRoute(routes);
        PagedResources <Route> pagedResources = assembler.toResource(routes,
                linkTo(RoutesRestController.class).slash("/").withSelfRel());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Link", createLinkHeader(pagedResources));
        return new ResponseEntity < PagedResources<Route>> (assembler.toResource(routes,
                linkTo(RoutesRestController.class).slash("/").withSelfRel()), responseHeaders, HttpStatus.OK);
    }

    private String createLinkHeader(PagedResources < Route > routePagedResources) {
        final StringBuilder linkHeader = new StringBuilder();
        linkHeader.append(buildLinkHeader(routePagedResources.getLinks("first").get(0).getHref(), "first"));
        linkHeader.append(", ");
        linkHeader.append(buildLinkHeader(routePagedResources.getLinks("next").get(0).getHref(), "next"));
        return linkHeader.toString();
    }

    private String buildLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

    private void addAirportLinksToRoute(Iterable<Route> routes){
        for (Route route : routes){
            Link link1 = entityLinks.linkToSingleResource(Airport.class, route.getFromAirport());
            Link link2 = entityLinks.linkToSingleResource(Airport.class, route.getDestinationAirport());
            route.add(Arrays.asList(link1, link2));
        }
    }
}
