package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "auditorium", schema = "public")
public class AuditoriumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screeningEntity;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "auditorium_seat",
            joinColumns = { @JoinColumn(name = "auditorium_id") },
            inverseJoinColumns = { @JoinColumn(name = "seat_id") }
    )
    Set<SeatEntity> seats = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setScreeningEntity(ScreeningEntity screeningEntity) {
        this.screeningEntity = screeningEntity;
    }

    public void setSeats(Set<SeatEntity> seats) {
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public ScreeningEntity getScreeningEntity() {
        return screeningEntity;
    }

    public Set<SeatEntity> getSeats() {
        return seats;
    }
}
