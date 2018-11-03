package com.codecool.restflights.Model;

import javax.persistence.*;

@Entity
@Table(name = "passengers")
public class Passenger {

    @Id
    @Column(name = "passenger_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passengerId;

    private String firstname;
    private String lastname;

    private boolean active;

    public Passenger() {
    }

    public Passenger(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
