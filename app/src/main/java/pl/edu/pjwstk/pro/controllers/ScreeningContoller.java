package pl.edu.pjwstk.pro.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.entities.ScreeningEntity;
import pl.edu.pjwstk.pro.requests.ScreeningRequest;
import pl.edu.pjwstk.pro.services.ScreeningService;

import java.util.List;

@RestController
public class ScreeningContoller {
    private ScreeningService screeningService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addScreening")
    public void addScreening(@RequestBody ScreeningRequest screeningRequest){
        screeningService.saveScreening(screeningRequest);
    }
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/addScreening/{screeningId}")
    public void editScreening(@PathVariable Long screeningId, @RequestBody ScreeningRequest screeningRequest){
        screeningService.editScreening(screeningId,screeningRequest);
    }
    @GetMapping("/getAllScreening")
    public List<ScreeningEntity> getAllScreening(){
        return screeningService.getAllScreening();
    }
    @GetMapping("/getSingleScreening/{screeningId}")
    public ScreeningEntity getSingleScreening(@PathVariable Long screeningId){
        return screeningService.getSingleScreening(screeningId);
    }



}
