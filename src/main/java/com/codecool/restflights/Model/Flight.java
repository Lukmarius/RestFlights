package com.codecool.restflights.Model;

import javax.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightId")
    private long flightId;

    @Column(name = "relationId")
    private long relationId;

    @Column(name = "planeId")
    private long planeId;

    private double price;

    @Column(name = "captainId")
    private long captainId;

    private String startdate;

    private String enddate;

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
