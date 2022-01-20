package com.everest.airline.flighthandler;

import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

import java.io.IOException;

public class FlightUpdate {
    private final int numberOfPassengers;
    private final Long number;
    private final String seatType;

    public FlightUpdate(Long number, String seatType, int numberOfPassengers) {
        this.number = number;
        this.seatType = seatType;
        this.numberOfPassengers = numberOfPassengers;
    }

    public Seats updateSeats(Flight flight) throws IOException {
        if (flight != null && flight.getNumber() == number) {
            FlightService flightService = new FlightService();
            Seats seats=new Seats();
            if (seatType.equals("Economic"))
               seats.setEconomicClass(seats.getEconomicClass() - numberOfPassengers);
            else if (seatType.equals("FirstClass"))
                seats.setFirstClass(seats.getFirstClass() - numberOfPassengers);
            else
                seats.setSecondClass(seats.getSecondClass() - numberOfPassengers);

            return seats;
        }

        throw new NullPointerException("Flight Data not updated");

    }

}
