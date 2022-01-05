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

            if (seatType.equals("Economic"))
                flight.getSeats().setEconomicClass(flight.getSeats().getEconomicClass() - numberOfPassengers);
            else if (seatType.equals("FirstClass"))
                flight.getSeats().setFirstClass(flight.getSeats().getFirstClass() - numberOfPassengers);
            else
                flight.getSeats().setSecondClass(flight.getSeats().getSecondClass() - numberOfPassengers);
            return flight.getSeats();
        }

        throw new NullPointerException("Flight Data not updated");

    }

}
