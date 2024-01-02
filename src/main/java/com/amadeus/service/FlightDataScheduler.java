package com.amadeus.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.amadeus.entity.Flight;
import com.amadeus.repository.FlightRepository;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Random;

@Service
public class FlightDataScheduler {

    private final FlightRepository flightRepository;

    public FlightDataScheduler(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(cron = "0 0 * * * *") // This runs the task every hour at minute 0.
    public void fetchFlightData() {
        Flight mockFlight = createMockFlight();
        flightRepository.save(mockFlight);
    }

    private Flight createMockFlight() {
        Flight flight = new Flight();
        // Set properties of the flight
        flight.setDepartureAirport("MockDepartureAirport");
        flight.setArrivalAirport("MockArrivalAirport");
        flight.setDepartureDateTime(LocalDateTime.now());
        flight.setReturnDateTime(LocalDateTime.now().plusDays(2));
        flight.setPrice(BigDecimal.valueOf(new Random().nextDouble() * 1000));
        return flight;
    }
}


