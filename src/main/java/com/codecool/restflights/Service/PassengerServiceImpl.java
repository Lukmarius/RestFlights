package com.codecool.restflights.Service;

import com.codecool.restflights.DAO.PassengersRepository;
import com.codecool.restflights.Model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private PassengersRepository passengersRepository;

    @Autowired
    public PassengerServiceImpl(PassengersRepository passengersRepository) {
        this.passengersRepository = passengersRepository;
    }

    @Override
    public Passenger findPassengerByPassengerId(long id) {
        return passengersRepository.findPassengerByPassengerId(id);
    }

    @Override
    public List<Passenger> findPassengersByFirstname(String name) {
        return passengersRepository.findPassengersByFirstname(name);
    }

    @Override
    public List<Passenger> findAll() {
        return passengersRepository.findAll();
    }

    @Override
    public Passenger save(Passenger passenger) {
        passengersRepository.save(passenger);
        return passenger;
    }

    @Override
    public void deletePassengerByPassengerId(long id) {
        passengersRepository.deletePassengerByPassengerId(id);
    }
}
