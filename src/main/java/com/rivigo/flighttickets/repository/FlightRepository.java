package com.rivigo.flighttickets.repository;

import com.rivigo.flighttickets.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    public Flight findByFlightNumber(String flightNumber);
}
