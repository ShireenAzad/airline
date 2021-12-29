package com.everest.airline.flighthandler;

import com.everest.airline.model.Flight;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightsData {
    public List<Flight> readFilesIntoList() throws IOException {
        ArrayList<Flight> flights = new ArrayList<>();
        File dir = new File("/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database");
        File[] files = dir.listFiles();
        if (files == null) throw new NullPointerException("Folder doesn't exist");
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try (BufferedReader inputStream = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = inputStream.readLine()) != null) {
                        FlightService flightService = new FlightService();
                        flights.add(flightService.newFlight(line));
                    }
                }
            }
        }
        return flights.stream().sorted(Comparator.comparing(Flight::getNumber)).collect(Collectors.toList());
    }
}