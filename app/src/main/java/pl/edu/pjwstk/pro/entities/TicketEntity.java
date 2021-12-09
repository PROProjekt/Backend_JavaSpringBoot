package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket",schema = "public")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="price")
    private double price;

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
