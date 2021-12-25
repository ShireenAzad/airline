package com.everest.airline.controller;

import com.everest.airline.DataHandler;
import com.everest.airline.database.FileHandler;
import com.everest.airline.model.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String search(String from, String to, String departureDate, Integer numberOfPassengers,String seatType, Model model) throws IOException {
        List<Flight> flightsSearch;
        FileHandler fileHandler=new FileHandler();
        flightsSearch= fileHandler.flightsSearch(from,to,departureDate,numberOfPassengers,seatType);
        if(flightsSearch.size()==0)
            return "searchHelper";
        model.addAttribute("flights", flightsSearch);
        return "search";
    }
    @RequestMapping(value="/book")
    public String booking(Long number,String seatType,String passengers, Model model) throws IOException {
        Flight flightsSearch;
        DataHandler dataHandler =new DataHandler(number,seatType,Integer.parseInt(passengers));
        flightsSearch= dataHandler.writeFile();
        model.addAttribute("flights", flightsSearch);
        return "book";
    }
    @RequestMapping(value = "/data")
    public String details(){
        return "data";

    }
}