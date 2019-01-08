package com.codecool.restflights.Config;

import com.codecool.restflights.Model.Airport;
import com.codecool.restflights.Model.Passenger;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

//    exposing Id for API in airport objects
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Airport.class);
        config.exposeIdsFor(Passenger.class);
    }
}