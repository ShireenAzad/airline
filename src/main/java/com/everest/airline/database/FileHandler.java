package com.everest.airline.database;

import com.everest.airline.Seat;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {
@Autowired FlightData flightData;
    public  List<Flight> flightsSearch(String from, String to, String Date, int numberOfPassengers, String seatType) {
        List<Flight> flightsSearch = new ArrayList<>();
        List<Flight> flights;
         flights = flightData.readFilesIntoList();
        int numberOfAvailableSeats;
        for (Flight flight : flights) {
            if (seatType.equals("Economic")) {
                Seat seat = new Seat(seatType, flight.getEconomicClassSeats(), flight.getEconomicFarePrice());
                flight.updateEconomicClassPrice(flight.getEconomicFarePrice() + (seat.getFarePrice() + seat.FarePrice(flight.getDepartureDate())));
                numberOfAvailableSeats = flight.getEconomicClassSeats();
            } else if (seatType.equals("FirstClass")) {
                Seat seat = new Seat(seatType, flight.getFirstClassSeats(), flight.getFirstClassPrice());
                flight.updateFirstClassPrice(flight.getFirstClassPrice() + (seat.getFarePrice() + seat.FarePrice(flight.getDepartureDate())));
                numberOfAvailableSeats = flight.getFirstClassSeats();
            } else {
                Seat seat = new Seat(seatType, flight.getSecondClassSeats(), flight.getSecondClassPrice());
                flight.updateSecondClassPrice(flight.getSecondClassPrice() + (seat.getFarePrice() + seat.FarePrice(flight.getDepartureDate())));
                numberOfAvailableSeats = flight.getSecondClassSeats();
            }
            if (flight.getSource().equals(from) && flight.getDestination().equals(to) && flight.getDepartureDate().equals(LocalDate.parse(Date)) && numberOfAvailableSeats > numberOfPassengers)
                flightsSearch.add(flight);
        }
        return flightsSearch.stream()
                .sorted(Comparator.comparing(Flight::getNumber))
                .collect(Collectors.toList());
    }
}