package com.everest.airline.flighthandler;

import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsSearch {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    class FlightRowMapper implements RowMapper<Flight> {
        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Flight flight = new Flight(rs.getLong("number"), rs.getString("source"), rs.getString("destination"), rs.getDate("departureDate").toLocalDate(), rs.getTime("arrivalTime").toLocalTime(), rs.getTime("departureTime").toLocalTime(), new Seats(rs.getLong("number"), rs.getInt("economicClass"), rs.getInt("firstClass"), rs.getInt("secondClass")), new FarePrice(rs.getLong("number"), rs.getInt("economicClassPrice"), rs.getInt("firstClassPrice"), rs.getInt("secondClassPrice")));
            return flight;
        }

    }

    public List<Flight> searchFlights(String from, String to, String Date, int numberOfPassengers, String seatType) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("from", from);
        map.put("to", to);
        if (seatType.equals("Economic"))
            return jdbcTemplate.query("SELECT * from flights inner join seats on flights.number =seats.number inner join farePrice on seats.number =farePrice.number and flights.source=:from and flights.destination=:to and seats.economicClass>=numberOfPassengers", map, new FlightsApiController.FlightRowMapper());
        if (seatType.equals("FirstClass"))
            return jdbcTemplate.query("SELECT * from flights inner join seats on flights.number =seats.number inner join farePrice on seats.number =farePrice.number and flights.source=:from and flights.destination=:to and seats.firstClass>=numberOfPassengers", map, new FlightsApiController.FlightRowMapper());
        return jdbcTemplate.query("SELECT * from flights inner join seats on flights.number =seats.number inner join farePrice on seats.number =farePrice.number and flights.source=:from and flights.destination=:to and seats.secondClass>=numberOfPassengers", map, new FlightsApiController.FlightRowMapper());

    }

    public Flight getSpecificFlight(Long number) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", number);
        return jdbcTemplate.queryForObject("select * from flights,seats,farePrice where flights.number=seats.number and seats.number=farePrice.number and flights.number=:number order by flights.number", map, new FlightsApiController.FlightRowMapper());

    }

    public long getLastFlightNumber() {
        File dir = new File("/Users/shireensyed/Desktop/airlines/src/main/java/com/everest/airline/database");
        File[] files = dir.listFiles();
        if (files == null) throw new NullPointerException("No such folder exist");
        Arrays.sort(files);
        String filename = files[files.length - 1].getName();
        long number = Long.parseLong(filename.substring(0, filename.indexOf('.'))) + 1;
        return number;
    }
}