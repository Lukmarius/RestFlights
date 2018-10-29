package com.codecool.restflights.DAO;

import com.codecool.restflights.Model.Airport;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "airports", collectionResourceRel = "airports")
public interface AirportsRepository extends PagingAndSortingRepository<Airport, String> {

    List<Airport> findAirportByCity(@Param("city") String city);
    List<Airport> findAirportByAirportId(@Param("id") String id);
    List<Airport> findAirportsByCountry(@Param("country") String country);

}
