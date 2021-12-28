package com.everest.airline;

import com.everest.airline.database.FlightsSearch;
import com.everest.airline.model.Flight;

import java.io.IOException;

public class BookTicket {
    public Flight ticketBooking(long number,String seatType,int numberOfPassengers) throws IOException {
        FlightsSearch flightsSearch=new FlightsSearch();
        Flight flight=flightsSearch.getSpecificFlight(number);
        FlightUpdate flightUpdate=new FlightUpdate(number,  seatType, numberOfPassengers);
        flight=flightUpdate.updateFlight(flight);
        FileWriting fileWriting=new FileWriting();
        fileWriting.writeFile(flight);
        return flight;
    }
}
