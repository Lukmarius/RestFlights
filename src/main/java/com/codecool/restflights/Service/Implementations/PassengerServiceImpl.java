package com.codecool.restflights.Service.Implementations;

import com.codecool.restflights.DAO.PassengersRepository;
import com.codecool.restflights.Model.Passenger;
import com.codecool.restflights.Service.Intarfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PassengerServiceImpl")
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
    public Page<Passenger> findAllOnPage(Pageable pageable) {
        return passengersRepository.findAll(pageable);
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
