package com.everest.airline.flighthandler;

import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@Component
public class FlightService {
    public Flight newFlight(String data) throws IOException {
        String[] flightData = data.split(",");
        long flightNumber = Long.parseLong(flightData[0].trim());
        String source = flightData[1];
        String destination = flightData[2];
        LocalDate departureDate = LocalDate.parse(flightData[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-d"));
        LocalTime arrivalTime = LocalTime.parse(flightData[4].trim(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime departureTime = LocalTime.parse(flightData[5].trim(), DateTimeFormatter.ofPattern("HH:mm"));
       Seats seats=flightSeats(data);
       FarePrice farePrice=seatsPrice(data);
        return new Flight(flightNumber, source, destination, departureDate, arrivalTime, departureTime);
    }

    public Seats flightSeats(String data) throws IOException {
        String[] flightData = data.split(",");
        int number=Integer.parseInt(flightData[0].trim());
        int economicSeats = Integer.parseInt(flightData[6].trim());
        int firstClassSeats = Integer.parseInt(flightData[7].trim());
        int secondClassSeats = Integer.parseInt(flightData[8].trim());
        return new Seats(number,economicSeats, firstClassSeats, secondClassSeats);

    }

    public FarePrice seatsPrice(String data) throws IOException {
        String[] flightData = data.split(",");
        int economicFarePrice = Integer.parseInt(flightData[9].trim());
        int firstClassFarePrice = Integer.parseInt(flightData[10].trim());
        int secondClassFarePrice = Integer.parseInt(flightData[11].trim());
        FarePrice farePrice = new FarePrice( economicFarePrice, firstClassFarePrice, secondClassFarePrice);
        return farePrice;

    }

}
