package com.codecool.restflights.Model;

import javax.persistence.*;


//
// ----------------------- NOT HANDLED BY ORM YET -----------------
//
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private long flightId;

    @Column(name = "relatio_id")
    private long relationId;

    @Column(name = "plane_id")
    private long planeId;

    private double price;

    @Column(name = "captain_id")
    private long captainId;

    private String startdate;

    private String enddate;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public void setRelationId(long relationId) {
        this.relationId = relationId;
    }

    public void setPlaneId(long planeId) {
        this.planeId = planeId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCaptainId(long captainId) {
        this.captainId = captainId;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public long getFlightId() {
        return flightId;
    }

    public long getRelationId() {
        return relationId;
    }

    public long getPlaneId() {
        return planeId;
    }

    public double getPrice() {
        return price;
    }

    public long getCaptainId() {
        return captainId;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }
}
