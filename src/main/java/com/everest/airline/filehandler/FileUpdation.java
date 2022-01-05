package com.everest.airline.filehandler;

import com.everest.airline.model.Flight;

import java.io.File;
import java.io.IOException;

public class FileUpdation {
    public Flight updateFile(Flight flight,long number) throws IOException {
        String filePath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        flight.setNumber(number);
        File file = new File(filePath);
        FileWriting fileWriting=new FileWriting();
        fileWriting.writeFile(flight,number,file);
        return flight;

    }
}
