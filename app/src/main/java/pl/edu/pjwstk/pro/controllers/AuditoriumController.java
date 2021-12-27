package pl.edu.pjwstk.pro.controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.requests.AuditoriumRequest;
import pl.edu.pjwstk.pro.services.AuditoriumService;

import java.util.List;

@RestController
public class AuditoriumController {
    private AuditoriumService auditoriumService;

    public AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/addAuditorium")
    public void addAuditorium(@RequestBody AuditoriumRequest auditoriumRequest){
        auditoriumService.saveAuditorium(auditoriumRequest);
    }
    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/editAuditorium/{auditoriumId}")
    public void editAuditorium(@PathVariable Long auditoriumId, @RequestBody AuditoriumRequest auditoriumRequest){
       auditoriumService.editAuditorium(auditoriumId,auditoriumRequest);
    }

    @GetMapping("/getSeats")
    public List<String> showSeats(){
        return auditoriumService.getSeats();
    }


}
