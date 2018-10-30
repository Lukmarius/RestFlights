package com.codecool.restflights.DAO;

import com.codecool.restflights.Model.Passenger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "passengers", collectionResourceRel = "passengers")
public interface PassengersRepository extends PagingAndSortingRepository<Passenger, Long> {


    Passenger findPassengerByPassengerId(@Param("id") long id);
    List<Passenger> findPassengersByFirstname(@Param("name") String name);
}
