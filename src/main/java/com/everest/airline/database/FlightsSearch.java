package com.everest.airline.database;

import com.everest.airline.FileWriting;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsSearch {
    public  List<Flight> searchFlights(String from, String to, String Date, int numberOfPassengers, String seatType) {
        List<Flight> flightsSearch = new ArrayList<>();
        FlightsData flightsData =new FlightsData();
        List<Flight> flights = flightsData.readFilesIntoList();
        int numberOfAvailableSeats;
        for (Flight flight : flights) {
            numberOfAvailableSeats=flight.getTotalSeats(seatType);
            if (flight.getSource().equals(from) && flight.getDestination().equals(to) && flight.getDepartureDate().equals(LocalDate.parse(Date)) && numberOfAvailableSeats > numberOfPassengers)
                flightsSearch.add(flight);
        }
        return flightsSearch.stream()
                .sorted(Comparator.comparing(Flight::getNumber))
                .collect(Collectors.toList());
    }
    public Flight getSpecificFlight(Long number) throws IOException {
        FlightsData flightsData =new FlightsData();
        List<Flight> flights = flightsData.readFilesIntoList();
        Flight specificFlight;
        specificFlight = flights.stream().filter(flight -> flight.getNumber() == number).findFirst().orElseThrow();

        return specificFlight;

    }
}