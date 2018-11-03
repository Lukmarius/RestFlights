package com.codecool.restflights.Service;

import com.codecool.restflights.DAO.PassengersRepository;
import com.codecool.restflights.Model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ActivePassengerServiceImpl")
public class ActivePassengerServiceImpl implements PassengerService {

    private PassengersRepository passengersRepository;

    @Autowired
    public ActivePassengerServiceImpl(PassengersRepository passengersRepository) {
        this.passengersRepository = passengersRepository;
    }

    @Override
    public Passenger findPassengerByPassengerId(long id) {
        return passengersRepository.findPassengerByPassengerIdAndActiveTrue(id);
    }

    @Override
    public List<Passenger> findPassengersByFirstname(String name) {
        return passengersRepository.findPassengersByFirstnameAndActiveTrue(name);
    }

    @Override
    public List<Passenger> findAll() {
        return passengersRepository.findAllByActiveTrue();
    }

    @Override
    public Passenger save(Passenger passenger) {
        passengersRepository.save(passenger);
        return passenger;
    }

    @Override
    public void deletePassengerByPassengerId(long id) {
    //        passengersRepository.deletePassengerByPassengerId(id);
    // deleting not allowed in this feature, just change Active column:

        Passenger passenger = passengersRepository.findPassengerByPassengerId(id);
        passenger.setActive(false);
        passengersRepository.save(passenger);
    }
}
