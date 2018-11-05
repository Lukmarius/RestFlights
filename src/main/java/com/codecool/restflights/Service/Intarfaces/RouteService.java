package com.codecool.restflights.Service.Intarfaces;

import com.codecool.restflights.Model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RouteService {

    List<Route> findRoutesByFromAirportAndDestinationAirport(String from, String to);
    List<Route> findRoutesByFromAirport(String from);
    List<Route> findRoutesByDestinationAirport(String to);
    Route findRouteByRelationId(long id);
    List<Route> findAll();
    Page<Route> findAll(int page, int size);
}
