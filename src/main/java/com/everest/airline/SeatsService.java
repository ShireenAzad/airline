package com.everest.airline;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class SeatsService {
    private int farePrice;
    private int totalSeats;
    private final int seats;
    private final String seatType;
    private final int ECONOMIC_SEATS = 30;
    private final int FIRST_CLASS_SEATS = 40;
    private final int SECOND_CLASS_SEATS = 30;

    public SeatsService(String seatType, int seats, int farePrice) {
        this.seatType = seatType;
        this.seats = seats;
        this.farePrice = farePrice;
    }

    public int getFarePrice() {

        if (seatType.equals("Economic"))
            totalSeats = ECONOMIC_SEATS;
        else if (seatType.equals("FirstClass"))
            totalSeats = FIRST_CLASS_SEATS;
        else
            totalSeats = SECOND_CLASS_SEATS;
        int numberOfSeats = (int) Math.round(30 * totalSeats / 100);
        if (seats <= numberOfSeats)
            return farePrice;
        int numberOfSeats1 = (int) Math.round(50 * totalSeats / 100);
        if (seats > numberOfSeats && seats <= numberOfSeats1)
            return farePrice = (int) ((20 / 100) * farePrice);
        int numberOfSeats2 = (int) Math.round(75 * totalSeats / 100);
        if (seats > numberOfSeats1 && seats <= numberOfSeats2)
            return (int) ((35 / 100) * farePrice);
        if (seats > numberOfSeats2 && seats <= totalSeats)
            return (int) ((50 / 100) * farePrice);
        return farePrice;

    }

    public int getFarePrice(LocalDate departureDate) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        long daysBetween = DAYS.between(parsedDate, departureDate);
        if (seatType.equals("Economic"))
            totalSeats = ECONOMIC_SEATS;
        else if (seatType.equals("FirstClass"))
            totalSeats = FIRST_CLASS_SEATS;
        else
            totalSeats = SECOND_CLASS_SEATS;
        if (daysBetween >= 15)
            return farePrice;
        if (daysBetween < 15 && daysBetween >= 10)
            return (int) (daysBetween * (1 / 100) * farePrice);
        if (daysBetween < 10 && daysBetween >= 3)
            return (int) (daysBetween * (2 / 100) * farePrice);

        return (int) (daysBetween * (10 / 100) * farePrice);

    }

    public int getTotalPrice(LocalDate departureDate) {
        return getFarePrice() + getFarePrice(departureDate) + farePrice;
    }
}