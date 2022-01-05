package com.everest.airline.model;

import com.everest.airline.enums.SeatType;

public class Seats {
    private int economicClass;
    private int firstClass;
    private int secondClass;
public Seats(){

}
    public Seats( int economicClass, int firstClass, int secondClass) {

        this.economicClass = economicClass;
        this.firstClass = firstClass;
        this.secondClass = secondClass;
    }

    public int getTotalSeats(String seatType) {
        if (seatType.equals(SeatType.Economic.toString()))
            return economicClass;
        if (seatType.equals(SeatType.FirstClass.toString()))
            return firstClass;
        return secondClass;
    }


    public void setEconomicClass(int economicClass) {
        this.economicClass = economicClass;
    }

    public void setFirstClass(int firstClass) {
        this.firstClass = firstClass;
    }

    public void setSecondClass(int secondClass) {
        this.secondClass = secondClass;
    }


    public int getEconomicClass() {
        return economicClass;
    }

    public int getFirstClass() {
        return firstClass;
    }

    public int getSecondClass() {
        return secondClass;
    }
}
