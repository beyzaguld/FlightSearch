package com.amadeus;

import com.amadeus.entity.Airport;
import com.amadeus.entity.Flight;
import com.amadeus.repository.AirportRepository;
import com.amadeus.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        Airport airport1 = new Airport();
        airport1.setCity("New York");
        airportRepository.save(airport1);

        Airport airport2 = new Airport();
        airport2.setCity("Los Angeles");
        airportRepository.save(airport2);

        Flight flight = new Flight();
        flight.setDepartureAirport("New York");
        flight.setArrivalAirport("Los Angeles");
        flight.setDepartureDateTime(LocalDateTime.of(2024, 1, 15, 8, 0));
        flight.setReturnDateTime(LocalDateTime.of(2024, 1, 20, 10, 0));
        flight.setPrice(new BigDecimal("300.00"));
        flightRepository.save(flight);
    }
}

