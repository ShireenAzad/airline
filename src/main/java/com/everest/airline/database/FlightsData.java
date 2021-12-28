package com.everest.airline.database;

import com.everest.airline.model.Flight;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class FlightsData {
    public List<Flight> readFilesIntoList() {
        ArrayList<Flight> flights = new ArrayList<>();
        File dir = new File("/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file!=null && file.isFile() && file.getName().endsWith(".txt")) {
                    String line;
                    try (BufferedReader inputStream = new BufferedReader(new FileReader(file))) {
                        while ((line = inputStream.readLine()) != null) {
                            if(line!=null) {
                                String[] flightData = line.split(",");
                                long flightNumber = Long.parseLong(flightData[0].trim());
                                String source = flightData[1];
                                String destination = flightData[2];
                                LocalDate departureDate = LocalDate.parse(flightData[3].trim(), DateTimeFormatter.ofPattern("yyyy-MM-d"));
                                LocalTime arrivalTime = LocalTime.parse(flightData[4].trim(), DateTimeFormatter.ofPattern("HH:mm"));
                                LocalTime departureTime = LocalTime.parse(flightData[5].trim(), DateTimeFormatter.ofPattern("HH:mm"));
                                int economicSeats = Integer.parseInt(flightData[6].trim());
                                int economicFarePrice = Integer.parseInt(flightData[7].trim());
                                int firstClassSeats = Integer.parseInt(flightData[8].trim());
                                int firstClassFarePrice = Integer.parseInt(flightData[9].trim());
                                int secondClassSeats = Integer.parseInt(flightData[10].trim());
                                int secondClassFarePrice = Integer.parseInt(flightData[11].trim());
                                Flight flight = new Flight(flightNumber, source, destination, departureDate, arrivalTime, departureTime, economicSeats, economicFarePrice, firstClassSeats, firstClassFarePrice, secondClassSeats, secondClassFarePrice);
                                flights.add(flight);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return flights.stream().sorted(Comparator.comparing(Flight::getNumber)).collect(Collectors.toList());
    }

}