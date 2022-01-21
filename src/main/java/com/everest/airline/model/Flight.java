package com.everest.airline.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private FarePrice farePrice;
    private Seats seats;
    private long number;
    private String source;
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    private LocalTime arrivalTime;
    private LocalTime departureTime;

    public Flight() {

    }

    public Flight(Long number, String source, String destination, LocalDate departureDate, LocalTime arrivalTime, LocalTime departureTime, Seats seats, FarePrice farePrice) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.seats = seats;
        this.farePrice = farePrice;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setSource() {
        this.source = source;
    }

    public void setDestination() {
        this.destination = destination;
    }

    public void setDepartureDate() {
        this.departureDate = departureDate;
    }

    public void setArrivalTime() {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureTime() {
        this.departureTime = departureTime;
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

    public Seats getSeats() throws IOException {
        return seats;
    }

    public FarePrice getFarePrice() throws IOException {

        return farePrice;
    }

}
