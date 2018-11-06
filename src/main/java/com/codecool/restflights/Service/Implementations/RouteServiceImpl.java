package com.codecool.restflights.Service.Implementations;

import com.codecool.restflights.DAO.RoutesRepository;
import com.codecool.restflights.Model.Route;
import com.codecool.restflights.Service.Intarfaces.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Route> findAllOnPage() {
        return routesRepository.findAll();
    }

    public Page<Route> findAllOnPage(int page, int size) {
        return routesRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public Page<Route> findAllOnPage(Pageable pageable) {
        return routesRepository.findAll(pageable);
    }


}
