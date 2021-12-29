package com.everest.airline.model;

public class FarePrice {
    private long flightNumber;
    private int economicClassPrice;
    private int firstClassPrice;
    private int secondClassPrice;

    public FarePrice() {

    }

    public FarePrice( int economicClassPrice, int firstClassPrice, int secondClassPrice) {

        this.economicClassPrice = economicClassPrice;
        this.firstClassPrice = firstClassPrice;
        this.secondClassPrice = secondClassPrice;

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
}
