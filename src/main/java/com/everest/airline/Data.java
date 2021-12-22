package com.everest.airline;

import com.everest.airline.database.FlightData;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public final class Data {
    private final long flightNumber;
    private final int economicSeats;
    private final int firstSeats;
    private final int secondSeats;

    public Data(final long flightNumber, final int economicSeats, final int firstSeats, final int secondSeats) {
        this.flightNumber = flightNumber;
        this.economicSeats = economicSeats;
        this.firstSeats = firstSeats;
        this.secondSeats = secondSeats;

    }

    public long getFlightNumber() {
        return flightNumber;
    }

    public int getEconomicSeats() {
        return this.economicSeats;
    }

    public int getFirstSeats() {
        return this.firstSeats;
    }

    public int getSecondSeats() {
        return this.secondSeats;
    }


}
