package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.requests.ScreeningRequest;
import pl.edu.pjwstk.pro.services.ScreeningService;

@RestController
public class ScreeningController {
    @Autowired
    ScreeningService service;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addScreening")
    public void addScreening(@RequestBody ScreeningRequest screeningRequest){
        service.saveScreening(screeningRequest.getDay(),screeningRequest.getTime());
    }
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/editScreening/{screeningId}")
    public void addScreening(@PathVariable Long screeningId, @RequestBody ScreeningRequest screeningRequest){
        service.editScreening(screeningId,screeningRequest.getDay(),screeningRequest.getTime());
    }

}