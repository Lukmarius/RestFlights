//package com.codecool.restflights.Model;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class Flight {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long flight_id;
//    private long relation_id;
//    private long plane_id;
//    private double price;
//    private long captain_id;
//    private String startdate;
//    private String enddate;
//
//    public void setFlight_id(long flight_id) {
//        this.flight_id = flight_id;
//    }
//
//    public void setRelation_id(long relation_id) {
//        this.relation_id = relation_id;
//    }
//
//    public void setPlane_id(long plane_id) {
//        this.plane_id = plane_id;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public void setCaptain_id(long captain_id) {
//        this.captain_id = captain_id;
//    }
//
//    public void setStartdate(String startdate) {
//        this.startdate = startdate;
//    }
//
//    public long getFlight_id() {
//        return flight_id;
//    }
//
//    public long getRelation_id() {
//        return relation_id;
//    }
//
//    public long getPlane_id() {
//        return plane_id;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public long getCaptain_id() {
//        return captain_id;
//    }
//
//    public String getStartdate() {
//        return startdate;
//    }
//
//    public String getEnddate() {
//        return enddate;
//    }
//}
