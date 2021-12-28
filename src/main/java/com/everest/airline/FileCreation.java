package com.everest.airline;

import com.everest.airline.database.FlightsData;
import com.everest.airline.model.Flight;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class FileCreation {
    @Autowired
    FlightsData flightsData;
    public long createFile(String flightJsonData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(flightJsonData, Map.class);
            if (map.size() == 11) {
                FlightsData flightsData = new FlightsData();
                List<Flight> data = flightsData.readFilesIntoList();
                long number = (data.get(data.size() - 1).getNumber()) + 1;
                Flight flight = new Flight(number, map.get("source"), map.get("destination"), LocalDate.parse(map.get("departureDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.parse(map.get("arrivalTime")), LocalTime.parse(map.get("departureTime")), Integer.parseInt(map.get("economicClassSeats")), Integer.valueOf(map.get("economicFarePrice")), Integer.parseInt(map.get("firstClassSeats")), Integer.valueOf(map.get("firstClassPrice")), Integer.valueOf(map.get("secondClassSeats")), Integer.valueOf(map.get("secondClassPrice")));
                String filePath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
                File file = new File(filePath);
                boolean result;
                try {
                    result = file.createNewFile();

                    if (result) {
                       String newFlightData = flight.getNumber() + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                                "," + flight.getArrivalTime() + "," + flight.getDepartureTime() + "," + flight.getEconomicClassSeats() + "," + flight.getEconomicFarePrice() +
                                "," + flight.getFirstClassSeats() + "," + flight.getFirstClassPrice() + "," + flight.getSecondClassSeats() + "," + flight.getSecondClassPrice();
                        FileWriter writer = new FileWriter(file);
                        writer.write(newFlightData);
                        writer.close();
                        return flight.getNumber();
                    } else
                        System.out.println("File already exist at location: " + file.getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 0;
    }
}