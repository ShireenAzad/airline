package com.everest.airline.flighthandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class SeatsService {
    private int farePrice;
    private int totalSeats;
    private final int seats;
    private final String seatType;
    private final int economicSeats = 30;
    private final int firstClassSeats = 40;
    private final int secondClassSeats = 30;

    public SeatsService(String seatType, int seats, int farePrice) {
        this.seatType = seatType;
        this.seats = seats;
        this.farePrice = farePrice;
    }

    public int getFarePrice() {

        if (seatType.equals("Economic"))
            totalSeats = economicSeats;
        else if (seatType.equals("FirstClass"))
            totalSeats = firstClassSeats;
        else
            totalSeats = secondClassSeats;
        int numberOfSeats = (int) Math.round(30 * totalSeats / 100);
        if (seats <= numberOfSeats)
            return farePrice;
        int numberOfSeats1 = (int) Math.round(50 * totalSeats / 100);
        if (seats > numberOfSeats && seats <= numberOfSeats1)
            return farePrice = (int) (farePrice + (20 / 100) * farePrice);
        int numberOfSeats2 = (int) Math.round(75 * totalSeats / 100);
        if (seats > numberOfSeats1 && seats <= numberOfSeats2)
            return (int) (farePrice + (35 / 100) * farePrice);
        if (seats > numberOfSeats2 && seats <= totalSeats)
            return (int) (farePrice + (50 / 100) * farePrice);
        return farePrice;

    }

    public int FarePrice(LocalDate departureDate) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        long daysBetween = DAYS.between(parsedDate, departureDate);
        if (seatType.equals("Economic"))
            totalSeats = economicSeats;
        else if (seatType.equals("FirstClass"))
            totalSeats = firstClassSeats;
        else
            totalSeats = secondClassSeats;
        if (daysBetween >= 15)
            return farePrice;
        else if (daysBetween < 15 && daysBetween >= 10)
            return (int) (farePrice + daysBetween * (1 / 100) * farePrice);
        else if (daysBetween < 10 && daysBetween >= 3)
            return (int) (farePrice + daysBetween * (2 / 100) * farePrice);
        else
            return (int) (farePrice + daysBetween * (10 / 100) * farePrice);

    }
}
