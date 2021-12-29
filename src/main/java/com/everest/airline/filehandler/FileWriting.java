package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightService;
import com.everest.airline.model.Seats;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
public class FileWriting {
    public void writeFile(Seats seats,long number) throws IOException {
        String directoryPath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/";
        String filename = number + ".txt";
        String filepath = directoryPath + filename;
        File file = new File(filepath);
        String fileData =  Files.readString(new File(filepath).toPath(), StandardCharsets.ISO_8859_1);
        FlightService flightService = new FlightService();
        Seats seats1 = flightService.flightSeats(number);
        String oldContent = seats1.getEconomicClass() + "," + seats1.getFirstClass() + "," + seats1.getSecondClass();
        String data = seats.getEconomicClass() + "," + seats.getFirstClass() + "," + seats.getSecondClass();
        FileWriter writer = new FileWriter(file);
        fileData = fileData.replaceAll(oldContent, data);
        writer.write(fileData);
        writer.close();

    }
}
