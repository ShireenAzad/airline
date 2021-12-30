package com.everest.airline.controller;

import com.everest.airline.flighthandler.BookTicket;
import com.everest.airline.flighthandler.FlightData;
import com.everest.airline.flighthandler.FlightsSearch;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {
    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(String from, String to, String departureDate, int numberOfPassengers, String seatType, Model model) throws IOException {
        List<FlightData> flights;
        FlightsSearch flightsSearch = new FlightsSearch();
        flights = flightsSearch.searchFlights(from, to, departureDate, numberOfPassengers, seatType);

        if (flights.size() == 0)
            return "searchHelper";
        model.addAttribute("flights", flights);
        model.addAttribute("seatType", seatType);
        model.addAttribute("passengers", numberOfPassengers);
        model.addAttribute("departureDate",departureDate);
        return "search";
    }

    @ModelAttribute
    @RequestMapping(value = "/book")
    public String booking(long number, String seatType, Integer numberOfPassengers, Model model) throws IOException {
        BookTicket bookTicket = new BookTicket();
        Seats seats = bookTicket.ticketBooking(number, seatType,numberOfPassengers);
        model.addAttribute("flight", seats);
        return "redirect:book.html";
    }

    @RequestMapping(value = "/data")
    public String details(@ModelAttribute() Long number) {
        return "data";

    }
}