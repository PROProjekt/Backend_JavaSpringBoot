package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ticket", schema = "public")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double price;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screeningEntity;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_ticket",
            joinColumns = { @JoinColumn(name = "ticket_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Set<UserEntity> users = new HashSet<>();

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public ScreeningEntity getScreeningEntity() {
        return screeningEntity;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setScreeningEntity(ScreeningEntity screeningEntity) {
        this.screeningEntity = screeningEntity;
    }
}
