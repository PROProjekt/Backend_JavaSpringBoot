package pl.edu.pjwstk.pro.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.entities.TicketEntity;
import pl.edu.pjwstk.pro.services.TicketService;

import java.util.List;


@RestController
public class TicketController {
    private TicketService ticketService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/setTicket")
    public void setTicket(@RequestParam double price) {
        ticketService.setPrice(price);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/editTicket/{ticketId}")
    public void editTicket(@PathVariable Long ticketId, @RequestBody double price){
        ticketService.editPrice(ticketId,price);
    }

    @GetMapping("/getSingleTicket/{ticketId}")
    public TicketEntity getSingleTicket(@PathVariable Long ticketId){
        return ticketService.getSingleTicket(ticketId);
    }
    @GetMapping("/getAllTickets")
    public List<TicketEntity> getSingleTicket(){
        return ticketService.getAllTickets();
    }

}
