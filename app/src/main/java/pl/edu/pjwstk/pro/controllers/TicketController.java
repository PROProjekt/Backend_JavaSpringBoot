package pl.edu.pjwstk.pro.controllers;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.services.TicketService;


@RestController
public class TicketController {
    private TicketService ticketService;

    @PostMapping("/setTicket")
    public void setTicket(@RequestBody double price) {
        ticketService.setPrice(price);
    }
    @PutMapping("/editTicket/{ticketId}")
    public void editTicket(@PathVariable Long ticketId, @RequestBody double price){
        ticketService.editPrice(ticketId,price);
    }
}
