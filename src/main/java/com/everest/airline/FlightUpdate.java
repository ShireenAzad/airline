package com.everest.airline;

import com.everest.airline.database.FlightsData;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class FlightUpdate {
    @Autowired
    FlightsData flightsData;
    @Autowired
    FileWriting fileWriting;
    private final int numberOfPassengers;
    private final Long number;
    private final String seatType;

    public FlightUpdate(Long number, String seatType, int numberOfPassengers) {
        this.number = number;
        this.seatType = seatType;
        this.numberOfPassengers = numberOfPassengers;
    }
    public Flight updateFlight(Flight flight) throws IOException {
            if (flight != null && flight.getNumber() == number) {
                if (seatType.equals("Economic"))
                    flight.updateEconomicClassSeats(flight.getEconomicClassSeats() - numberOfPassengers);
                else if (seatType.equals("FirstClass"))
                    flight.updateFirstClassSeats(flight.getFirstClassSeats() - numberOfPassengers);
                else
                    flight.updateSecondClassSeats(flight.getSecondClassSeats() - numberOfPassengers);
                return flight;
            }

        throw new NullPointerException("Flight Data not updated");

    }

}
