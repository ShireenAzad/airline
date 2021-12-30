package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightData;
import com.everest.airline.flighthandler.FlightService;
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
    public void updatedDataToFile(Seats updatedSeats,long number) throws IOException {
        String directoryPath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/";
        String filename = number + ".txt";
        String filepath = directoryPath + filename;
        File file = new File(filepath);
        String fileData =  Files.readString(new File(filepath).toPath(), StandardCharsets.ISO_8859_1);
        FlightService flightService = new FlightService();
        Seats seats= flightService.flightSeats(number);
        String pastSeatsData = seats.getEconomicClass() + "," + seats.getFirstClass() + "," + seats.getSecondClass();
        String updatedSeatsData = updatedSeats.getEconomicClass() + "," + updatedSeats.getFirstClass() + "," + updatedSeats.getSecondClass();
        FileWriter writer = new FileWriter(file);
        fileData = fileData.replaceAll(pastSeatsData,updatedSeatsData);
        writer.write(fileData);
        writer.close();

    }

    public void writeFile(FlightData flightData,long number,File file) throws IOException {
        Flight flight=flightData.getFlight();
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
    }
}
