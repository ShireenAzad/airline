package com.everest.airline.flighthandler;

import com.everest.airline.filehandler.FileWriting;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

import java.io.IOException;

public class BookTicket {
    public Seats ticketBooking(long number, String seatType, int numberOfPassengers) throws IOException {
        FlightsSearch flightsSearch=new FlightsSearch();
        Flight flight1=flightsSearch.getSpecificFlight(number);
        FlightUpdate flightUpdate=new FlightUpdate(number,  seatType, numberOfPassengers);
        Seats seats =flightUpdate.updateSeats(flight1);
        FileWriting fileWriting=new FileWriting();
        fileWriting.writeFile(seats,number);
        return seats;
    }
}
