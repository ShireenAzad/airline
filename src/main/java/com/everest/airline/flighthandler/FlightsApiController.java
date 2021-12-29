package com.everest.airline.flighthandler;

import com.everest.airline.filehandler.FileCreation;
import com.everest.airline.filehandler.FileDeletion;
import com.everest.airline.filehandler.FileUpdation;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController

public class FlightsApiController {
@Autowired
FlightsData flightsData;
    @GetMapping("/flights")
    public List<Flight> getAllFlights() throws IOException {
            List<Flight>data=flightsData.readFilesIntoList();
            return data;


    }
    @GetMapping("/flights/{number}")
    public Flight getSpecificFlight(@PathVariable String number) throws IOException {
        FlightsSearch flightsSearch=new FlightsSearch();
        return flightsSearch.getSpecificFlight(Long.parseLong(number));
    }

    // CUD
    @PostMapping(value = "/flights", consumes = MediaType.ALL_VALUE)

    @ResponseBody
    public long create(@RequestBody FlightData flightData) throws IOException {
        FileCreation fileCreation=new FileCreation();
               return fileCreation.createFile(flightData);
    }

    // Update
    @PutMapping("/flights/{number}")
    public Flight update(@RequestBody FlightData flightData,@PathVariable long number) throws IOException {
        FileUpdation fileUpdation=new FileUpdation();
                return fileUpdation.updateFile(flightData,number);
    }

    // Update
    @DeleteMapping("/flights/{number}")
    public void delete(@PathVariable long number) throws IOException {
        FileDeletion fileDeletion = new FileDeletion();
        fileDeletion.deleteFile(number);
    }
}
