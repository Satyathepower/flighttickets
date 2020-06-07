package com.rivigo.flighttickets.repository;

import com.rivigo.flighttickets.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    public Flight findByFlightNumber(String flightNumber);
}
