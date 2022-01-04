package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.requests.SeatRequest;
import pl.edu.pjwstk.pro.services.SeatService;

@RestController
public class SeatController {

    @Autowired
    SeatService service;
    @PostMapping("/addSeat")
    public void addSeat(@RequestBody SeatRequest seatRequest){
        service.saveSeat(seatRequest);
    }

    @PutMapping("/setTaken/{seatId}")
    public void setSeatTaken(@PathVariable Long seatId){
        service.setSeatTaken(seatId);
    }
    @PutMapping("/setAvailable/{seatId}")
    public void setSeatAvailable(@PathVariable Long seatId){
        service.setSeatAvailable(seatId);
    }



}
