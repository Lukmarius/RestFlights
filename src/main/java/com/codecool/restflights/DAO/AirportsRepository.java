package com.codecool.restflights.DAO;

import com.codecool.restflights.Model.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    List<Airport> findAirportsByCityAndActiveTrue(@Param("city") String city);
    Airport findAirportByAirportIdAndActiveTrue(@Param("id") String id);
    List<Airport> findAirportsByCountryAndActiveTrue(@Param("country") String country);
    List<Airport> findAllByActiveTrue();
    Page<Airport>
    findAirportsByAirportIdIsContainingOrCityIsContainingOrCountryIsContaining(@Param("word") String word,
                                                                                             @Param("word") String word1,
                                                                                             @Param("word") String word2,
                                                                                             Pageable pageable);

}
