package com.codecool.restflights.Service;

import com.codecool.restflights.Model.Route;

import java.util.List;

public interface RouteService {

    List<Route> findRoutesByFromAirportAndDestinationAirport(String from, String to);
    List<Route> findRoutesByFromAirport(String from);
    List<Route> findRoutesByDestinationAirport(String to);
    Route findRouteByRelationId(long id);
    List<Route> findAll();
}
