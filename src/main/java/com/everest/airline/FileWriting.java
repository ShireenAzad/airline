package com.everest.airline;

import com.everest.airline.model.Flight;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
@Component
public class FileWriting {
    public void writeFile(Flight flight) throws IOException {
        String directoryPath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/";
        String filename = flight.getNumber()+ ".txt";
        String filepath = directoryPath + filename;
        File file = new File(filepath);
        String data = flight.getNumber() + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                "," + flight.getArrivalTime() + "," + flight.getDepartureTime() + "," + flight.getEconomicClassSeats() + "," + flight.getEconomicFarePrice() +
                "," + flight.getFirstClassSeats() + "," + flight.getFirstClassPrice() + "," + flight.getSecondClassSeats() + "," + flight.getSecondClassPrice();
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        System.out.println(data);
        writer.close();

    }
}
