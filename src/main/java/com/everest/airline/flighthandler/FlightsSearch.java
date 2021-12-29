package com.everest.airline.flighthandler;

import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsSearch {
    public List<Flight> searchFlights(String from, String to, String Date, int numberOfPassengers, String seatType) throws IOException {
        List<Flight> flightsSearch = new ArrayList<>();
        FlightsData flightsData = new FlightsData();
        List<Flight> flights = flightsData.readFilesIntoList();
        flights=flights.stream()
                .sorted(Comparator.comparing(Flight::getNumber))
                .collect(Collectors.toList());
        for (Flight flight : flights) {
            FlightService flightService=new FlightService();
                Seats seats=flightService.flightSeats(flight.getNumber());
            int numberOfAvailableSeats= seats.getTotalSeats(seatType);
            if (flight.getSource().equals(from) && flight.getDestination().equals(to) && flight.getDepartureDate().equals(LocalDate.parse(Date)) && numberOfAvailableSeats > numberOfPassengers)
                flightsSearch.add(flight);
        }
        return flightsSearch.stream()
                .sorted(Comparator.comparing(Flight::getNumber))
                .collect(Collectors.toList());
    }

    public Flight getSpecificFlight(long number) throws IOException {
        String file = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        String contents = Files.readString(new File(file).toPath(), StandardCharsets.ISO_8859_1);
        FlightService flightService = new FlightService();
        return flightService.newFlight(contents);
    }
}