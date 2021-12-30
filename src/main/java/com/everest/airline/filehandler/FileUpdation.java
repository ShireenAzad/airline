package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightData;
import com.everest.airline.model.Flight;

import java.io.File;
import java.io.IOException;

public class FileUpdation {
    public Flight updateFile(FlightData flightData,long number) throws IOException {
        String filePath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        Flight flight=flightData.getFlight();
        flight.setNumber(number);
        File file = new File(filePath);
        FileWriting fileWriting=new FileWriting();
        fileWriting.writeFile(flightData,number,file);
        return flight;

    }
}
