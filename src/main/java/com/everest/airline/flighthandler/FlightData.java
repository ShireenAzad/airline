package com.everest.airline.flighthandler;

import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

public class FlightData {
    private Flight flight;
    private Seats seats;
    private FarePrice farePrice;
    FlightData(){

    }
    public FlightData(Flight flight, Seats seats, FarePrice farePrice) {
        this.flight=flight;
        this.seats=seats;
        this.farePrice=farePrice;
    }

    public Flight getFlight() {
        return flight;
    }

    public Seats getSeats() {
        return seats;
    }
    public FarePrice getFarePrice(){
        return farePrice;
    }
}
