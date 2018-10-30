package com.codecool.restflights.DAO;

import com.codecool.restflights.Model.Airport;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "airports", collectionResourceRel = "airports")
public interface AirportsRepository extends PagingAndSortingRepository<Airport, String> {

    List<Airport> findAirportsByCity(@Param("city") String city);
    Airport findAirportByAirportId(@Param("id") String id);
    List<Airport> findAirportsByCountry(@Param("country") String country);
    List<Airport> findAll();

}
