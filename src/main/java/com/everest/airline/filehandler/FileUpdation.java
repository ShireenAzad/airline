package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightData;
import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

import java.io.FileWriter;
import java.io.IOException;

public class FileUpdation {
    public Flight updateFile(FlightData flightData,long number) throws IOException {
        String file = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        Flight flight=flightData.getFlight();
        flight.setNumber(number);
        if(flight==null) throw new NullPointerException("Flight object is Null");
        Seats seats= flightData.getSeats();
        if(seats==null) throw new NullPointerException("Flight seats Object is null");
        FarePrice farePrice= flightData.getFarePrice();
        if(farePrice==null) throw new NullPointerException("Fare Price of Flights seats are null");
        String newFlightData = number + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                "," + flight.getArrivalTime() + "," + flight.getDepartureTime()+","+ seats.getEconomicClass()+","+ farePrice.getEconomicClassPrice()+","+
                seats.getFirstClass()+","+ farePrice.getFirstClassPrice()+","+ seats.getSecondClass()+","+ farePrice.getSecondClassPrice();
        FileWriter writer = new FileWriter(file);
        writer.write(newFlightData);
        writer.close();
        return flight;

    }
}
