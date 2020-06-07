package com.rivigo.flighttickets.service;

import com.rivigo.flighttickets.entity.Flight;

public interface FlightService {

    public Flight saveFlight(Flight flight) ;

    public Flight getFlightDetail(String flightNumber) ;
}
