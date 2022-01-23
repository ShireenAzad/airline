package com.everest.airline.controller;

import com.everest.airline.flighthandler.BookTicket;
import com.everest.airline.flighthandler.FlightsApiController;
import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    class FlightRowMapper implements RowMapper<Flight> {
        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Flight flight = new Flight(rs.getLong("number"), rs.getString("source"), rs.getString("destination"), rs.getDate("departureDate").toLocalDate(), rs.getTime("arrivalTime").toLocalTime(), rs.getTime("departureTime").toLocalTime(), new Seats(rs.getLong("number"), rs.getInt("economicClass"), rs.getInt("firstClass"), rs.getInt("secondClass")), new FarePrice(rs.getLong("number"), rs.getInt("economicClassPrice"), rs.getInt("firstClassPrice"), rs.getInt("secondClassPrice")));
            return flight;
        }

    }

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(String from, String to, String departureDate, int numberOfPassengers, String seatType, Model model) throws IOException {
        List<Flight> flights;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("from", from);
        map.put("to", to);
        map.put("numberOfPassengers",numberOfPassengers);
        if (seatType.equals("Economic"))
            flights = jdbcTemplate.query("SELECT * from flights inner join seats on flights.number =seats.number inner join farePrice on seats.number =farePrice.number and flights.source=:from and flights.destination=:to and seats.economicClass>:numberOfPassengers", map, new FlightsApiController.FlightRowMapper());
        if (seatType.equals("FirstClass"))
            flights = jdbcTemplate.query("SELECT * from flights inner join seats on flights.number =seats.number inner join farePrice on seats.number =farePrice.number and flights.source=:from and flights.destination=:to and seats.firstClass>:numberOfPassengers", map, new FlightsApiController.FlightRowMapper());
        else
            flights = jdbcTemplate.query("SELECT * from flights inner join seats on flights.number =seats.number inner join farePrice on seats.number =farePrice.number and flights.source=:from and flights.destination=:to and seats.secondClass>:numberOfPassengers", map, new FlightsApiController.FlightRowMapper());

        if (flights.size() == 0)
            return "searchHelper";
        model.addAttribute("flights", flights);
        model.addAttribute("seatType", seatType);
        model.addAttribute("passengers", numberOfPassengers);
        model.addAttribute("departureDate", departureDate);
        return "search";

    }

    @ModelAttribute
    @RequestMapping(value = "/book")
    public String booking(Long number, String seatType, Integer numberOfPassengers, Model model) throws IOException {
        BookTicket bookTicket = new BookTicket();
        Seats seats = bookTicket.ticketBooking(number, seatType, numberOfPassengers);
        model.addAttribute("flight", seats);
        return "redirect:data/{number}";
    }

    @RequestMapping(value = "/data/{number}")
    public String details(@PathVariable Long number) {
        return "data";

    }
}