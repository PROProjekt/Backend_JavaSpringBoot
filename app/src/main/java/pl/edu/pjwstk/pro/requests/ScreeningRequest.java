package pl.edu.pjwstk.pro.requests;

import pl.edu.pjwstk.pro.entities.TicketEntity;

public class ScreeningRequest {
    private String day;
    private String time;
    private TicketEntity ticket;

    public ScreeningRequest(String day, String time, TicketEntity ticket) {
        this.day = day;
        this.time = time;
        this.ticket = ticket;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
}
