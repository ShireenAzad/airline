package com.everest.airline.flighthandler;

import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Flights {
    public List<Flight> readFilesIntoList() throws IOException {
        ArrayList<Flight> flights = new ArrayList<>();
        File dir = new File("/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database");
        File[] files = dir.listFiles();
        if (files == null) throw new NullPointerException("Folder doesn't exist");
        Arrays.sort(files);
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try (BufferedReader inputStream = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = inputStream.readLine()) != null) {
                        FlightService flightService = new FlightService();
                        Flight flight=flightService.newFlight(line);
                        flights.add(flight);
                    }
                }
            }
        }
        return flights;
    }
}