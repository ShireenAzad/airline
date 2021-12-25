package com.everest.airline;

import com.everest.airline.database.FlightData;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class FileUpdation {
    public void updateFile(long number, Flight flight) throws IOException {
        FlightData flightData=new FlightData();
        List<Flight> flights = flightData.readFilesIntoList();
        String directoryPath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/";
        String filename = number + ".txt";
        String filepath = directoryPath + filename;
        File file = new File(filepath);
        String data = flight.getNumber() + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                "," + flight.getArrivalTime() + "," + flight.getDepartureTime() + "," + flight.getEconomicClassSeats() + "," + flight.getEconomicFarePrice() +
                "," + flight.getFirstClassSeats() + "," + flight.getFirstClassPrice() + "," + flight.getSecondClassSeats() + "," + flight.getSecondClassPrice();
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.close();

    }
}
