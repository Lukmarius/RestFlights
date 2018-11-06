package com.codecool.restflights.Service.Intarfaces;

import com.codecool.restflights.Model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PassengerService {

    Passenger findPassengerByPassengerId(long id);
    List<Passenger> findPassengersByFirstname(String name);

    List<Passenger> findAll();
    Page<Passenger> findAllOnPage(Pageable pageable);
    Passenger save(Passenger passenger);

    void deletePassengerByPassengerId(long id);
}
