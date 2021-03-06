package com.codecool.restflights.DAO;

import com.codecool.restflights.Model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "routes", collectionResourceRel = "routes")
public interface RoutesRepository extends JpaRepository<Route, Long> {

    Route findRouteByRelationId(@Param("id") long id);
    List<Route> findRoutesByFromAirportAndDestinationAirport(@Param("from") String from, @Param("to") String to);
    List<Route> findRoutesByFromAirport(@Param("from") String from);
    List<Route> findRoutesByDestinationAirport(@Param("to") String to);
    List<Route> findAll();
    Page<Route> findAllByActiveTrue(Pageable pageable);

}
