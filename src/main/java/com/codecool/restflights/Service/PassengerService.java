package com.codecool.restflights.Service;

import com.codecool.restflights.Model.Passenger;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PassengerService {

    Passenger findPassengerByPassengerId(long id);
    List<Passenger> findPassengersByFirstname(String name);

    List<Passenger> findAll();
    Passenger save(Passenger passenger);

    void deletePassengerByPassengerId(long id);
}
