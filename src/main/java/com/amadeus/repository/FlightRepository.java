package com.amadeus.repository;

import com.amadeus.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime);

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime);

}


