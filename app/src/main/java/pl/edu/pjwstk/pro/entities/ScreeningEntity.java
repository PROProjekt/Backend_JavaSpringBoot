package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;

@Entity
@Table(name = "screening",schema = "public")
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="day")
    private String day;
    @Column(name="time")
    private String time;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;

    public void setId(Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
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
}
