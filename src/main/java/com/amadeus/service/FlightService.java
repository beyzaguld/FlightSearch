package com.amadeus.service;

import com.amadeus.entity.Flight;
import com.amadeus.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Get all flights
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    // Get a single flight by ID
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    // Create a new flight
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    // Update an existing flight
    public Optional<Flight> updateFlight(Long id, Flight flightDetails) {
        return flightRepository.findById(id).map(flight -> {
            flight.setDepartureAirport(flightDetails.getDepartureAirport());
            flight.setArrivalAirport(flightDetails.getArrivalAirport());
            flight.setDepartureDateTime(flightDetails.getDepartureDateTime());
            flight.setReturnDateTime(flightDetails.getReturnDateTime());
            flight.setPrice(flightDetails.getPrice());
            return flightRepository.save(flight);
        });
    }

    // Delete a flight
    public boolean deleteFlight(Long id) {
        return flightRepository.findById(id).map(flight -> {
            flightRepository.delete(flight);
            return true;
        }).orElse(false);
    }

    // Search for flights
    public List<Flight> searchFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {
        if (returnDateTime == null) {
            // Logic for one-way flights
            return flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(departureAirport, arrivalAirport, departureDateTime);
        } else {
            // Logic for round-trip flights
            // This may involve finding two flights: one for going and one for returning
            List<Flight> flights = new ArrayList<>();
            flights.addAll(flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(departureAirport, arrivalAirport, departureDateTime));
            flights.addAll(flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDateTime(arrivalAirport, departureAirport, returnDateTime));
            return flights;
        }
    }

}

