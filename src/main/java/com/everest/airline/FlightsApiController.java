package com.everest.airline;

import com.everest.airline.database.FlightsData;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class FlightsApiController {
@Autowired
FlightsData flightsData;
    @GetMapping({"/flights", "/flights/{number}"})
    public List<Flight> getAllFlights(@PathVariable Optional<String> number) {
        List<Flight> data = flightsData.readFilesIntoList();
        List<Flight> flightData = new ArrayList<Flight>();
        if (number.isPresent()) {
            flightData = data.stream().filter(flight -> String.valueOf(flight.getNumber()).equals(number.get())).collect(Collectors.toList());
            return flightData;
        }
        data.forEach(flight -> System.out.println(flight.getNumber()));
        return data;
    }

    // CUD
    @PostMapping(value = "/flights", consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public long create(@RequestBody String data1) {
        FileCreation fileCreation=new FileCreation();
               return fileCreation.createFile(data1);
    }

    // Update
    @PutMapping("/flights/{number}")
    @ResponseBody
    public Flight update(@RequestBody String updatedFlightData, @PathVariable long number, String source, String destination) {
        FileUpdation fileUpdation=new FileUpdation();
                return fileUpdation.updateFile(updatedFlightData,number);
    }

    // Update
    @DeleteMapping("/flights/{number}")
    public void delete(@PathVariable long number) throws IOException {
        FileDeletion fileDeletion = new FileDeletion();
        fileDeletion.deleteFile(number);
    }
}
