package com.codecool.restflights.Service.Implementations;

import com.codecool.restflights.DAO.AirportsRepository;
import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Service.Interfaces.AirportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportsServiceImpl implements AirportsService {

    private final AirportsRepository airportsRepository;

    @Autowired
    public AirportsServiceImpl(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    @Override
    public void deleteById(String id) throws NullPointerException {
        Airport airport = airportsRepository.findAirportByAirportIdAndActiveTrue(id);
        airport.setActive(false);
        airportsRepository.save(airport);
    }
}
