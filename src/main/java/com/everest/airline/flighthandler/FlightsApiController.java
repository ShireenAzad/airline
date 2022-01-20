package com.everest.airline.flighthandler;

import com.everest.airline.filehandler.FileCreation;
import com.everest.airline.filehandler.FileDeletion;
import com.everest.airline.filehandler.FileUpdation;
import com.everest.airline.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
    class FlightRowMapper implements RowMapper<Flight> {
        @Override
        public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
             Flight flight=new Flight(rs.getLong("number"), rs.getString("source"), rs.getString("destination"),rs.getDate("departureDate").toLocalDate(),rs.getTime("arrivalTime").toLocalTime(),rs.getTime("departureTime").toLocalTime());
            return flight;
        }

    }
//get All flights

    @GetMapping("/flights")
    public List<Flight> getAllFlights() throws IOException {
        return jdbcTemplate.query("select * from flights", new FlightRowMapper());
    }
    @GetMapping("/flights/{number}")
    public Flight getSpecificFlight(@PathVariable String number) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", number);
        return jdbcTemplate.queryForObject("select * from flights where number = :number", map, new FlightRowMapper());
    }

    // CUD
    @PostMapping(value = "/flights", consumes = MediaType.ALL_VALUE)

    @ResponseBody
    public long create(@RequestBody Flight flight) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number",flight.getNumber());
        map.put("source", flight.getSource());
        map.put("destination", flight.getDestination());
        map.put("departureDate",flight.getDepartureDate());
        map.put("arrivalTime",flight.getArrivalTime());
        map.put("departureTime",flight.getDepartureTime());
       return jdbcTemplate.update("insert into flights(number,source, destination,departureDate,arrivalTime,departureTime) values (:number,:source, :destination,:departureDate,:arrivalTime,:departureTime)", map);
    }


//    // Update
//    @PutMapping("/flights/{number}")
//    public Flight update(@RequestBody Flight flight,@PathVariable long number) throws IOException {
//        final String UPDATE_QUERY = "update employee set age = ? where id = ?";
//        return
//
//    }

    // Update
    @DeleteMapping("/flights/{number}")
    public boolean delete(@PathVariable long number) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", number);
        return jdbcTemplate.update( "DELETE FROM flights WHERE number = :number", map) == 1;
    }
}
