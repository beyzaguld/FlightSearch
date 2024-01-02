package com.amadeus.service;

import com.amadeus.entity.Airport;
import com.amadeus.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // Get all airports
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Get a single airport by ID
    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    // Create a new airport
    public Airport createAirport(Airport airport) {
        // You might want to add additional logic here to handle any business rules or validations
        return airportRepository.save(airport);
    }

    // Update an existing airport
    public Optional<Airport> updateAirport(Long id, Airport airportDetails) {
        return airportRepository.findById(id).map(airport -> {
            airport.setCity(airportDetails.getCity());
            // Add other fields here if your Airport entity has more than just an ID and a city
            return airportRepository.save(airport);
        });
    }

    // Delete an airport
    public boolean deleteAirport(Long id) {
        return airportRepository.findById(id).map(airport -> {
            airportRepository.delete(airport);
            return true;
        }).orElse(false);
    }
}

