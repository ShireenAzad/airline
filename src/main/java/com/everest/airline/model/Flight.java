package com.everest.airline.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
public class Flight {
    private final long number;
    private final String source;
    private final String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate departureDate;
    private final LocalTime arrivalTime;
    private final LocalTime departureTime;
    private int economicClassSeats;
    private int economicFarePrice;
    private int firstClassSeats;
    private int firstClassPrice;
    private int secondClassSeats;
    private int secondClassPrice;


    public Flight(long number, String source, String destination, LocalDate departureDate, LocalTime arrivalTime, LocalTime departureTime, int economicClassSeats, int economicFarePrice, int firstClassSeats, int firstClassPrice, int secondClassSeats, int secondClassPrice) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.economicClassSeats = economicClassSeats;
        this.economicFarePrice = economicFarePrice;
        this.firstClassSeats = firstClassSeats;
        this.firstClassPrice = firstClassPrice;
        this.secondClassSeats = secondClassSeats;
        this.secondClassPrice = secondClassPrice;

    }
    public int getEconomicClassSeats() {
        return economicClassSeats;
    }
    public void updateEconomicClassPrice(int price){this.economicFarePrice=price;}
    public void updateFirstClassPrice(int price){this.firstClassPrice=price;}
    public void updateSecondClassPrice(int price){this.secondClassPrice=price;}
    public void updateEconomicClassSeats(int seats) {
        this.economicClassSeats = seats;
    }

    public void updateFirstClassSeats(int seats) {
        this.firstClassSeats = seats;
    }

    public void updateSecondClassSeats(int seats) {
        this.secondClassSeats = seats;
    }

    public long getNumber() {
        return number;
    }

    public String getSource() {

        return source;
    }

    public String getDestination() {

        return destination;
    }


    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }


    public int getEconomicFarePrice() {
        return economicFarePrice;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public int getSecondClassSeats() {
        return secondClassSeats;
    }

    public int getFirstClassPrice() {
        return firstClassPrice;
    }

    public int getSecondClassPrice() {
        return secondClassPrice;
    }


}

