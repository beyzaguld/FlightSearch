package com.amadeus.repository;

import com.amadeus.entity.Airport;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
	
    // Find an airport by city.
    Optional<Airport> findByCity(String city);
}

