package com.everest.airline;
import com.everest.airline.model.Flight;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class FileCreation {

    private final String flightJsonData;

    public FileCreation(String flightData) {
        this.flightJsonData=flightData;
    }

    public  void createFile(long number, Flight flight)
    {
        String filePath="/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/"+number+".txt";
        File file = new File(filePath);
        boolean result;
        try
        {
            result = file.createNewFile();

            if(result)
            {
                String data = flight.getNumber() + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                        "," + flight.getArrivalTime() + "," + flight.getDepartureTime() + "," + flight.getEconomicClassSeats() + "," + flight.getEconomicFarePrice() +
                        "," + flight.getFirstClassSeats() + "," + flight.getFirstClassPrice() + "," + flight.getSecondClassSeats() + "," + flight.getSecondClassPrice();
                FileWriter writer = new FileWriter(file);
                writer.write(data);
                writer.close();
            }
            else
                System.out.println("File already exist at location: "+file.getCanonicalPath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}