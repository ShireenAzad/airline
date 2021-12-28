package com.everest.airline.controller;

import com.everest.airline.BookTicket;
import com.everest.airline.database.FlightsSearch;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String search(String from, String to, String departureDate, Integer numberOfPassengers, String seatType, Model model) throws IOException {
        List<Flight> flights;
        FlightsSearch flightsSearch = new FlightsSearch();
        flights = flightsSearch.searchFlights(from, to, departureDate, numberOfPassengers, seatType);
        if (flights.size() == 0)
            return "searchHelper";
        model.addAttribute("flights", flights);
        model.addAttribute("seatType", seatType);
        model.addAttribute("passengers", numberOfPassengers);
        return "search";
    }

    @ModelAttribute
    @RequestMapping(value = "/book")
    public String booking(Long number, String seatType, int numberOfPassengers, Model model) throws IOException {
        BookTicket bookTicket = new BookTicket();
        Flight flight = bookTicket.ticketBooking(number, seatType, numberOfPassengers);
        model.addAttribute("flight", flight);
        return "redirect:book.html";
    }

    @RequestMapping(value = "/data")
    public String details(@ModelAttribute() Long number) {

        System.out.println(number);
        return "data";

    }
}