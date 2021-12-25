package com.everest.airline;

public final class Data {
    private long flightNumber;
    private int economicSeats;
    private int firstSeats;
    private int secondSeats;

    public Data(long flightNumber, int economicSeats, int firstSeats, int secondSeats) {
        this.firstSeats = firstSeats;
        this.flightNumber = flightNumber;
        this.secondSeats = secondSeats;
        this.economicSeats = economicSeats;

    }

    public void setEconomicSeats(int economicSeats) {
        this.economicSeats = this.economicSeats;
    }

    public void setFirstSeats(int firstSeats) {
        this.firstSeats = firstSeats;
    }

    public void setSecondSeats(int secondSeats) {
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
