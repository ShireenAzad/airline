package com.everest.airline;

import com.everest.airline.database.FlightData;
import com.everest.airline.model.Flight;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController

public class FlightsApiController {

    FlightData flightData=new FlightData();
    @GetMapping({"/flights", "/flights/{number}"})
    public List<Flight> getAllFlights(@PathVariable Optional<String> number) {

        List<Flight> data = flightData.readFilesIntoList();
        List<Flight> flightData = new ArrayList<Flight>();
        if (number.isPresent()) {
            flightData = data.stream().filter(flight ->String.valueOf(flight.getNumber()).equals(number.get())).collect(Collectors.toList());
            return flightData;
        }
        data.forEach(flight -> System.out.println(flight.getNumber()));
        return data;
    }

    // CUD
    @PostMapping(value = "/flights",consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public long create(@RequestBody String data1) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(data1, Map.class);
            if(map.size()==11){
                List<Flight> data = flightData.readFilesIntoList();
                long number = data.get(data.size() - 1).getNumber();
                Flight flight=new Flight(number+1,map.get("source"),map.get("destination"),LocalDate.parse(map.get("departureDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalTime.parse(map.get("arrivalTime")),LocalTime.parse(map.get("departureTime")),Integer.parseInt(map.get("economicClassSeats")),Integer.valueOf(map.get("economicFarePrice")),Integer.parseInt(map.get("firstClassSeats")),Integer.valueOf(map.get("firstClassPrice")),Integer.valueOf(map.get("secondClassSeats")),Integer.valueOf(map.get("secondClassPrice")));
                //Flight flight = new Flight(number + 1, "Hyderabad", "Bangalore", LocalDate.of(2022, 01, 10), LocalTime.of(10, 12), LocalTime.of(12, 12), 39, 1500, 54, 1000, 30, 800);
                FileCreation fileCreation = new FileCreation(data1);
                fileCreation.createFile(number + 1, flight);
                return flight.getNumber();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Update
    @PutMapping("/flights/{number}")
    @ResponseBody
    public Flight update(@RequestBody String updatedFlightData,@PathVariable long number, String source, String destination) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = mapper.readValue(updatedFlightData, Map.class);
            if(map.size()==12){
                List<Flight> data = flightData.readFilesIntoList();
                Flight existingFlight = data.stream().filter(f -> f.getNumber() == number).findFirst().orElse(null);
                System.out.println(existingFlight);
                Flight flight=new Flight(number,map.get("source"),map.get("destination"),LocalDate.parse(map.get("departureDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalTime.parse(map.get("arrivalTime")),LocalTime.parse(map.get("departureTime")),Integer.parseInt(map.get("economicClassSeats")),Integer.valueOf(map.get("economicFarePrice")),Integer.parseInt(map.get("firstClassSeats")),Integer.valueOf(map.get("firstClassPrice")),Integer.valueOf(map.get("secondClassSeats")),Integer.valueOf(map.get("secondClassPrice")));
               data.set(data.indexOf(existingFlight),flight);
               FileUpdation fileUpdation=new FileUpdation();
               fileUpdation.updateFile(number,flight);
                return flight;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    @DeleteMapping("/flights/{number}")
    public void delete(@PathVariable long number) {
        FileDeletion fileDeletion = new FileDeletion();
        try {
            fileDeletion.deleteFile(number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
