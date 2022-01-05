package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightsSearch;
import com.everest.airline.model.Flight;

import java.io.File;
import java.io.IOException;

public class FileCreation {
    public long createFile(Flight flight) throws IOException {
        FlightsSearch flightsSearch=new FlightsSearch();
        long number=flightsSearch.getLastFlightNumber();
        String filePath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        File file = new File(filePath);
        boolean result;
        result = file.createNewFile();
        if (!result)
            throw new NullPointerException("File already exist at location: " + file.getCanonicalPath());
        FileWriting fileWriting=new FileWriting();
        fileWriting.writeFile(flight,number,file);
        return number;

    }

}
