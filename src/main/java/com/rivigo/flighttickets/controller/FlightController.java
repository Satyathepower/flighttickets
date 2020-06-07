package com.rivigo.flighttickets.controller;

import com.rivigo.flighttickets.constant.Constant;
import com.rivigo.flighttickets.dto.*;
import com.rivigo.flighttickets.entity.Flight;
import com.rivigo.flighttickets.entity.User;
import com.rivigo.flighttickets.service.FlightService;
import com.rivigo.flighttickets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    // localhost:9191/scheduleFlight

   /* {   "flightNumber":"sat",
            "noOfSeats":10
    }*/

    @PostMapping(path= "/scheduleFlight" )
    public ResponseEntity<Object> scheduleFlight(@RequestBody Flight flight) {
        Flight flight1=  flightService.getFlightDetail(flight.getFlightNumber());
        if(flight1 ==null){
            flightService.saveFlight(flight);
            Status status=new Status();
            status.setStatus(Constant.SUCCESS);
            return new ResponseEntity<Object>(status, HttpStatus.OK);
        }else {
            FailedStatus status = new FailedStatus();
            status.setStatus(Constant.FAILED);
            status.setMessage(Constant.FLIGHT_EXISTS);
            return new ResponseEntity<Object>(status, HttpStatus.OK);
        }
    }

    // localhost:9191/getAvailableSeats?flightNumber=sat

    @GetMapping(path="/getAvailableSeats")
    public ResponseEntity<Object> getAvailableSeats(@RequestParam String flightNumber){
        Flight flight1=  flightService.getFlightDetail(flightNumber);
        if(flight1 !=null) {
            FlightSeatStatus status = new FlightSeatStatus();
            status.setCount(flight1.getNoOfSeats());
            status.setStatus(Constant.SUCCESS);
            return new ResponseEntity<Object>(status, HttpStatus.OK);
        }else {
            Status status = new Status();
            status.setStatus(Constant.FAILED);
            return new ResponseEntity<Object>(status, HttpStatus.OK);
        }

    }

    // localhost:9191/bookSeat

/*    {   "flightNumber":"ade",
            "userName":"Jone"
    }*/


    @PostMapping(path="/bookSeat")
    public ResponseEntity<Object> bookSeat(@RequestBody BookSeat bookSeat) {

        Flight flight1 = flightService.getFlightDetail(bookSeat.getFlightNumber());
        if (flight1 != null) {
            if (flight1.getNoOfSeats() > Constant._ZERO) {
                User user = new User();
                user.setName(bookSeat.getUserName());
                user.setSeatNumber(flight1.getNoOfSeats());
                userService.saveUser(user);

                flight1.setNoOfSeats(flight1.getNoOfSeats() - Constant._ONE);
                flightService.saveFlight(flight1);

                BookSuccessStatus status = new BookSuccessStatus();
                status.setStatus(Constant.SUCCESS);
                status.setSeatNumber(flight1.getNoOfSeats()+Constant._ONE);
                return new ResponseEntity<Object>(status, HttpStatus.OK);
            } else {
                FailedStatus failedStatus = new FailedStatus();
                failedStatus.setStatus(Constant.FAILED);
                failedStatus.setMessage(Constant.TICKETS_FULL);
                return new ResponseEntity<Object>(failedStatus, HttpStatus.OK);
            }

        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
