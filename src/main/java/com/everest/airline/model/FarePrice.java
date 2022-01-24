package com.everest.airline.model;

import com.everest.airline.SeatsService;
import com.everest.airline.enums.SeatType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FarePrice {
    private Long number;
    private int economicClassPrice;
    private int firstClassPrice;
    private int secondClassPrice;

    public FarePrice() {

    }

    public FarePrice(Long number,int economicClassPrice, int firstClassPrice, int secondClassPrice) {
        this.number=number;
        this.economicClassPrice = economicClassPrice;
        this.firstClassPrice = firstClassPrice;
        this.secondClassPrice = secondClassPrice;

    }
    public Long getNumber() {
        return number;
    }

    public void setEconomicClassPrice(int economicClassPrice) {
        this.economicClassPrice = economicClassPrice;
    }

    public void setFirstClassPrice(int firstClassPrice) {
        this.firstClassPrice = firstClassPrice;
    }

    public void setSecondClassPrice(int secondClassPrice) {
        this.secondClassPrice = secondClassPrice;
    }


    public int getEconomicClassPrice() {
        return economicClassPrice;
    }

    public int getFirstClassPrice() {
        return firstClassPrice;
    }

    public int getSecondClassPrice() {
        return secondClassPrice;
    }

    public int getTotalFarePrice(String seatType,String departureDate) {
        Seats seats = new Seats();
        LocalDate date = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("yyyy-MM-d"));
        if (seatType.equals(SeatType.Economic.toString())) {

            SeatsService seatsService = new SeatsService(seatType, seats.getTotalSeats(seatType), getEconomicClassPrice());
            return seatsService.getTotalPrice(date);
        }

        if (seatType.equals(SeatType.FirstClass.toString())) {
            SeatsService seatsService = new SeatsService(seatType, seats.getTotalSeats(seatType), getFirstClassPrice());
            return seatsService.getTotalPrice(date);
        }
        SeatsService seatsService = new SeatsService(seatType, seats.getTotalSeats(seatType), getSecondClassPrice());
        return seatsService.getTotalPrice(date);
    }
}
