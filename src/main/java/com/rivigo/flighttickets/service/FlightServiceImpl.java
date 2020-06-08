package com.rivigo.flighttickets.service;

import com.rivigo.flighttickets.entity.Flight;
import com.rivigo.flighttickets.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
   private FlightRepository flightRepository;

    @Transactional
    public Flight saveFlight( Flight flight){
       return flightRepository.save(flight);
    }

    public Flight getFlightDetail(String flightNumber){
        return flightRepository.findByFlightNumber(flightNumber);
    }
}
