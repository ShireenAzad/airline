package com.everest.airline.model;

public class FlightDetails {
    private Flight flight;
    private Seats seats;
    private FarePrice farePrice;
    FlightDetails(){

    }
    FlightDetails( Flight flight,Seats seats,FarePrice farePrice){
        this.flight=flight;
        this.seats=seats;
        this.farePrice=farePrice;

    }

    public Flight getFlight() {
        return flight;
    }

    public FarePrice getFarePrice() {
        return farePrice;
    }

    public Seats getSeats() {
        return seats;
    }
}
