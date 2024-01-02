package com.amadeus.controller;

import com.amadeus.entity.Flight;
import com.amadeus.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        return flightService.updateFlight(id, flightDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        return flightService.deleteFlight(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    

@GetMapping("/search")
public ResponseEntity<List<Flight>> searchFlights(
        @RequestParam String departureAirport,
        @RequestParam String arrivalAirport,
        @RequestParam String departureDateTimeStr,
        @RequestParam(required = false) String returnDateTimeStr) {

    try {
        LocalDateTime departureDateTime = LocalDateTime.parse(departureDateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime returnDateTime = returnDateTimeStr != null ? LocalDateTime.parse(returnDateTimeStr, DateTimeFormatter.ISO_DATE_TIME) : null;

        List<Flight> flights = flightService.searchFlights(departureAirport, arrivalAirport, departureDateTime, returnDateTime);
        return ResponseEntity.ok(flights);
    } catch (DateTimeParseException e) {
        // Handle the parse exception, maybe return a bad request response
        return ResponseEntity.badRequest().body(null); // Modify as needed
    }
}


}

