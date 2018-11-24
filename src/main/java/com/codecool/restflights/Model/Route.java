package com.codecool.restflights.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
@Table(name = "routes")
public class Route extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private long relationId;

    @Column(name = "fromAirport")
    private String fromAirport;

    @Column(name = "destinationAirport")
    private String destinationAirport;

    private int distance;

//    private Airport from;

    @JsonIgnore
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public long getRelationId() {
        return relationId;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
