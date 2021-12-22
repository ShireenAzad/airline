package com.everest.airline;

import com.everest.airline.database.FlightData;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataHandler {
    private static int numberOfPassengers;
    private static Long number;
    private static String seatType;

    public DataHandler(Long number, String seatType, int numberOfPassengers) {
        DataHandler.number = number;
        DataHandler.seatType = seatType;
        DataHandler.numberOfPassengers = numberOfPassengers;
    }

    public static Flight writeFile() throws IOException {
        List<Flight> flights = FlightData.readFilesIntoList(numberOfPassengers, seatType);
        String directoryPath = "/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database/";
        String filename = number + ".txt";
        String filepath = directoryPath + filename;
        File file = new File(filepath);
        for (Flight flight : flights) {
            if (flight != null && flight.getNumber() == number) {
                if (seatType.equals("Economic")){

                    flight.updateEconomicClassSeats(flight.getEconomicClassSeats() - numberOfPassengers);}
                else if (seatType.equals("FirstClass"))
                    flight.updateFirstClassSeats(flight.getFirstClassSeats() - numberOfPassengers);
                else
                    flight.updateSecondClassSeats(flight.getSecondClassSeats() - numberOfPassengers);
                String data = flight.getNumber() + "," + flight.getSource() + "," + flight.getDestination() + "," + flight.getDepartureDate() +
                        "," + flight.getArrivalTime() + "," + flight.getDepartureTime() + "," + flight.getEconomicClassSeats() + "," + flight.getEconomicFarePrice() +
                        "," + flight.getFirstClassSeats() + "," + flight.getFirstClassPrice() + "," + flight.getSecondClassSeats() + "," + flight.getSecondClassPrice();
                FileWriter writer = new FileWriter(file);
                writer.write(data);
                writer.close();
                return flight;
            }
        }

        return null;

    }

}
