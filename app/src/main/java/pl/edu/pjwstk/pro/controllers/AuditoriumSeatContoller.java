package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.pro.requests.AuditroiumSeatsRequest;
import pl.edu.pjwstk.pro.services.AuditoriumSeatsService;

@RestController
public class AuditoriumSeatContoller {
    @Autowired
    AuditoriumSeatsService service;

    @PostMapping("/setSeatsInAuditorium")
    public void connect(@RequestBody AuditroiumSeatsRequest request){
        service.connect(request.getAuditoriumId(), request.getSeatId());
    }

}
