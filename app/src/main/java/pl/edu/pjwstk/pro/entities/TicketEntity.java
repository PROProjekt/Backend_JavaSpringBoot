package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket", schema = "public")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="price")
    private double price;
    @JoinColumn(name = "screening_id")
    private Long screeningId;

    @ManyToOne
    @JoinColumn(name = "screening_id",insertable = false, updatable = false)
    private ScreeningEntity screening;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public void setScreening(ScreeningEntity screening) {
        this.screening = screening;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public ScreeningEntity getScreening() {
        return screening;
    }
}
