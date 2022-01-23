package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightService;
import com.everest.airline.flighthandler.FlightsSearch;
import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class FileWriting {
    public void updatedDataToFile(Seats updatedSeats, Long number) throws IOException {
        FlightsSearch flightsSearch=new FlightsSearch();
        flightsSearch.getSpecificFlight(number);
        String directoryPath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/";
        String filename = number + ".txt";
        String filepath = directoryPath + filename;
        File file = new File(filepath);
        String fileData =  Files.readString(new File(filepath).toPath(), StandardCharsets.ISO_8859_1);
        FlightService flightService = new FlightService();
        Seats seats= flightService.flightSeats(fileData);
        String pastSeatsData = seats.getEconomicClass() + "," + seats.getFirstClass() + "," + seats.getSecondClass();
        String updatedSeatsData = updatedSeats.getEconomicClass() + "," + updatedSeats.getFirstClass() + "," + updatedSeats.getSecondClass();
        FileWriter writer = new FileWriter(file);
        fileData = fileData.replaceAll(pastSeatsData,updatedSeatsData);
        writer.write(fileData);
        writer.close();

    }

    public void writeFile(Flight flight,long number,File file) throws IOException {
        if(flight==null) throw new NullPointerException("Flight object is Null");
        Seats seats=new Seats();
        if(seats==null) throw new NullPointerException("Flight seats Object is null");
        FarePrice farePrice=new FarePrice();
        if(farePrice==null) throw new NullPointerException("Fare Price of Flights seats are null");
        String newFlightData = number + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                "," + flight.getArrivalTime() + "," + flight.getDepartureTime()+","+ seats.getEconomicClass()+","+ farePrice.getEconomicClassPrice()+","+
                seats.getFirstClass()+","+ farePrice.getFirstClassPrice()+","+ seats.getSecondClass()+","+ farePrice.getSecondClassPrice();
        FileWriter writer = new FileWriter(file);
        writer.write(newFlightData);
        writer.close();
    }
}
