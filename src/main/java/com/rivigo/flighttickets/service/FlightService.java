package com.rivigo.flighttickets.service;

import com.rivigo.flighttickets.entity.Flight;
import org.springframework.transaction.annotation.Transactional;

public interface FlightService {


    public Flight saveFlight(Flight flight) ;

    public Flight getFlightDetail(String flightNumber) ;
}
