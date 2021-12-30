package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightData;
import com.everest.airline.flighthandler.FlightsSearch;
import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileCreation {
    public long createFile(FlightData flightData) throws IOException {
        FlightsSearch flightsSearch=new FlightsSearch();
        long number=flightsSearch.getLastFlightNumber();
        String filePath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        File file = new File(filePath);
        boolean result;
        result = file.createNewFile();
        if (!result)
            throw new NullPointerException("File already exist at location: " + file.getCanonicalPath());
        FileWriting fileWriting=new FileWriting();
        fileWriting.writeFile(flightData,number,file);
        return number;

    }

}
