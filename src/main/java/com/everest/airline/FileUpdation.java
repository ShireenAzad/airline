package com.everest.airline;

import com.everest.airline.database.FlightsData;
import com.everest.airline.model.Flight;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class FileUpdation {
    public Flight updateFile(String updatedFlightedFileData, long number) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(updatedFlightedFileData, Map.class);
            if (map.size() == 12) {
                FlightsData flightsData =new FlightsData();
                List<Flight> data = flightsData.readFilesIntoList();
                Flight existingFlight = data.stream().filter(f -> f.getNumber() == number).findFirst().orElse(null);
                Flight flight = new Flight(number, map.get("source"), map.get("destination"), LocalDate.parse(map.get("departureDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.parse(map.get("arrivalTime")), LocalTime.parse(map.get("departureTime")), Integer.parseInt(map.get("economicClassSeats")), Integer.valueOf(map.get("economicFarePrice")), Integer.parseInt(map.get("firstClassSeats")), Integer.valueOf(map.get("firstClassPrice")), Integer.valueOf(map.get("secondClassSeats")), Integer.valueOf(map.get("secondClassPrice")));
                data.set(data.indexOf(existingFlight), flight);
                FileWriting fileWriting=new FileWriting();
                fileWriting.writeFile(flight);
                return flight;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
