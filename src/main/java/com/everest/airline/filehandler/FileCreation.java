package com.everest.airline.filehandler;

import com.everest.airline.flighthandler.FlightData;
import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileCreation {
    public long createFile(FlightData flightData) throws IOException {
        File dir = new File("/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database");
        File[] files = dir.listFiles();
        if(files==null)throw new NullPointerException("No such folder exist");
        Arrays.sort(files);
        String filename=files[files.length - 1].getName();
        long number= Long.parseLong(filename.substring(0,filename.indexOf('.')))+1;
        String filePath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/" + number + ".txt";
        File file = new File(filePath);
        boolean result;
        result = file.createNewFile();
        if (!result)
            throw new NullPointerException("File already exist at location: " + file.getCanonicalPath());
        Flight flight=flightData.getFlight();
        if(flight==null) throw new NullPointerException("Flight object is Null");
        Seats seats= flightData.getSeats();
        if(seats==null) throw new NullPointerException("Flight seats Object is null");
        FarePrice farePrice= flightData.getFarePrice();
        if(farePrice==null) throw new NullPointerException("Fare Price of Flights seats are null");
        String newFlightData = number + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                "," + flight.getArrivalTime() + "," + flight.getDepartureTime()+","+ seats.getEconomicClass()+","+ farePrice.getEconomicClassPrice()+","+
                seats.getFirstClass()+","+ farePrice.getFirstClassPrice()+","+ seats.getSecondClass()+","+ farePrice.getSecondClassPrice();
        FileWriter writer = new FileWriter(file);
        writer.write(newFlightData);
        writer.close();
        return number;

    }

}
