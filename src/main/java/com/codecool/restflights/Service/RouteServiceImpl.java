package com.codecool.restflights.Service;

import com.codecool.restflights.DAO.RoutesRepository;
import com.codecool.restflights.Model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private RoutesRepository routesRepository;

    @Autowired
    public RouteServiceImpl(RoutesRepository routesRepository) {
        this.routesRepository = routesRepository;
    }

    @Override
    public List<Route> findRoutesByFromAirportAndDestinationAirport(String from, String to) {
        return routesRepository.findRoutesByFromAirportAndDestinationAirport(from, to);
    }

    @Override
    public List<Route> findRoutesByFromAirport(String from) {
        return routesRepository.findRoutesByFromAirport(from);
    }

    @Override
    public List<Route> findRoutesByDestinationAirport(String to) {
        return routesRepository.findRoutesByDestinationAirport(to);
    }

    @Override
    public Route findRouteByRelationId(long id) {
        return routesRepository.findRouteByRelationId(id);
    }

    @Override
    public List<Route> findAll() {
        return routesRepository.findAll();
    }
}
