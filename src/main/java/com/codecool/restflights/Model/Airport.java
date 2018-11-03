package com.codecool.restflights.Model;
import javax.persistence.*;

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @Column(name = "airport_id")
    private String airportId;

    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAirportId() {
        return airportId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
