package com.everest.airline.flighthandler;

import com.everest.airline.model.FarePrice;
import com.everest.airline.model.Flight;
import com.everest.airline.model.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class FlightsApiController {
    @Autowired
    Flights flights;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public static class FlightRowMapper implements RowMapper<Flight> {
        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
            Flight flight = new Flight(rs.getLong("number"), rs.getString("source"), rs.getString("destination"), rs.getDate("departureDate").toLocalDate(), rs.getTime("arrivalTime").toLocalTime(), rs.getTime("departureTime").toLocalTime(), new Seats(rs.getLong("number"), rs.getInt("economicClass"), rs.getInt("firstClass"), rs.getInt("secondClass")), new FarePrice(rs.getLong("number"), rs.getInt("economicClassPrice"), rs.getInt("firstClassPrice"), rs.getInt("secondClassPrice")));
            return flight;
        }

    }
//get All flights

    @GetMapping("/flights")
    public List<Flight> getAllFlights() throws IOException {
       return jdbcTemplate.query("SELECT * from flights inner join seats on flights.number =seats.number inner join farePrice on seats.number =farePrice.number ; ", new FlightRowMapper());
     }

    @GetMapping("/flights/{number}")
    public Flight getSpecificFlight(@PathVariable String number) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", number);
        return jdbcTemplate.queryForObject("select * from flights,seats,farePrice where flights.number=seats.number and seats.number=farePrice.number and flights.number=:number order by flights.number", map, new FlightRowMapper());
    }

    // CUD
    @PostMapping(value = "/flights", consumes = MediaType.ALL_VALUE)

    @ResponseBody
    public long create(@RequestBody Flight flight) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", flight.getNumber());
        map.put("source", flight.getSource());
        map.put("destination", flight.getDestination());
        map.put("departureDate", flight.getDepartureDate());
        map.put("arrivalTime", flight.getArrivalTime());
        map.put("departureTime", flight.getDepartureTime());
        map.put("economicClass", flight.getSeats().getEconomicClass());
        map.put("firstClass", flight.getSeats().getFirstClass());
        map.put("secondClass", flight.getSeats().getSecondClass());
        map.put("economicClassPrice", flight.getFarePrice().getEconomicClassPrice());
        map.put("firstClassPrice", flight.getFarePrice().getFirstClassPrice());
        map.put("secondClassPrice", flight.getFarePrice().getSecondClassPrice());
        jdbcTemplate.update("insert into flights(number,source, destination,departureDate,arrivalTime,departureTime) values (:number,:source, :destination,:departureDate,:arrivalTime,:departureTime)", map);
        jdbcTemplate.update("insert into seats(number,economicClass,firstClass,secondClass) values(:number,:economicClass,:firstClass,:secondClass)", map);
        jdbcTemplate.update("insert into farePrice(number,economicClassPrice,firstClassPrice,secondClassPrice) values(:number,:economicClassPrice,:firstClassPrice,:secondClassPrice)", map);
        return flight.getNumber();
    }


    // Update
    @PutMapping("/flights/{number}")
    public long update(@RequestBody Flight flight) throws IOException {

        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("number",flight.getNumber())
                .addValue("source", flight.getSource())
                .addValue("destination", flight.getDestination())
                .addValue("departureDate", flight.getDepartureDate())
                .addValue("arrivalTime", flight.getArrivalTime())
                .addValue("departureTime", flight.getDepartureTime())
                .addValue("economicClass", flight.getSeats().getEconomicClass())
                .addValue("firstClass", flight.getSeats().getFirstClass())
                .addValue("secondClass", flight.getSeats().getSecondClass())
                .addValue("economicClassPrice", flight.getFarePrice().getEconomicClassPrice())
                .addValue("firstClassPrice", flight.getFarePrice().getFirstClassPrice())
                .addValue("secondClassPrice", flight.getFarePrice().getSecondClassPrice());
        jdbcTemplate.update("update flights set source=:source, destination=:destination,departureDate=:departureDate,arrivalTime=:arrivalTime,departureTime=:departureTime where flights.number=:number", paramSource);
        jdbcTemplate.update("update seats set economicClass=:economicClass,firstClass=:firstClass,secondClass=:secondClass where number=:number", paramSource);
        jdbcTemplate.update("update farePrice set economicClassPrice=:economicClassPrice,firstClassPrice=:firstClassPrice,secondClassPrice=:secondClassPrice where number=:number", paramSource);

        return flight.getNumber();

    }

    // Update
    @DeleteMapping("/flights/{number}")
    public boolean delete(@PathVariable long number) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", number);
        return jdbcTemplate.update("DELETE FROM flights WHERE number = :number", map) == 1;
    }
}
