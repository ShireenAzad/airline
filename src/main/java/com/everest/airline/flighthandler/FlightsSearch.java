package com.everest.airline.flighthandler;

import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightsSearch {
    public List<Flight> searchFlights(String from, String to, String Date, int numberOfPassengers, String seatType) throws IOException {
        List<Flight> flightsSearch = new ArrayList<>();
        Flights flights = new Flights();
        List<Flight> flightsInformation = flights.readFilesIntoList();
        for (Flight flight : flightsInformation) {

            FlightService flightService = new FlightService();
            int numberOfAvailableSeats = flight.getSeats().getTotalSeats(seatType);
            if (flight.getSource().equals(from) && flight.getDestination().equals(to) && flight.getDepartureDate().equals(LocalDate.parse(Date)) && numberOfAvailableSeats > numberOfPassengers)
                flightsSearch.add(flight);


        }
        return flightsSearch;
    }

    public Flight getSpecificFlight(long number) throws IOException {
        String file = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        String contents = Files.readString(new File(file).toPath(), StandardCharsets.ISO_8859_1);
        FlightService flightService = new FlightService();
        return flightService.newFlight(contents);
    }

    public long getLastFlightNumber() {
        File dir = new File("/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database");
        File[] files = dir.listFiles();
        if (files == null) throw new NullPointerException("No such folder exist");
        Arrays.sort(files);
        String filename = files[files.length - 1].getName();
        long number = Long.parseLong(filename.substring(0, filename.indexOf('.'))) + 1;
        return number;
    }
}