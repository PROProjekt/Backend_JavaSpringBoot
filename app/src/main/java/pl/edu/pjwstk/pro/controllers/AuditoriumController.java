package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.pro.requests.AuditoriumRequest;
import pl.edu.pjwstk.pro.responses.AuditoriumCollection;
import pl.edu.pjwstk.pro.services.AuditoriumService;

@RestController
public class AuditoriumController {
    @Autowired
    AuditoriumService auditoriumService;

    @PostMapping("/addAuditorium")
    public void saveAuditorium(@RequestBody AuditoriumRequest request){
        auditoriumService.addAuditorium(request);
    }

    @GetMapping("/getSeatsInAuditoriums")
    public ResponseEntity<AuditoriumCollection> getAuditoriums(){
       return ResponseEntity.ok(auditoriumService.findAuditoriums());
    }
}
