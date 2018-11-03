package com.codecool.restflights.DAO;

import com.codecool.restflights.Model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "passengers", collectionResourceRel = "passengers")
public interface PassengersRepository extends JpaRepository<Passenger, Long> {


    Passenger findPassengerByPassengerId(@Param("id") long id);
    List<Passenger> findPassengersByFirstname(@Param("name") String name);
    List<Passenger> findAll();
    void deletePassengerByPassengerId(long id);

    Passenger save(Passenger passenger);

    Passenger findPassengerByPassengerIdAndActiveTrue(@Param("id") long id);
    List<Passenger> findPassengersByFirstnameAndActiveTrue(@Param("name") String name);
    List<Passenger> findAllByActiveTrue();

}
