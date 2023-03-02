package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seat", schema = "public")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String seat_number;
    private boolean available;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "auditorium_seat",
            joinColumns = { @JoinColumn(name = "seat_id") },
            inverseJoinColumns = { @JoinColumn(name = "auditorium_id") }
    )
    Set<AuditoriumEntity> auditoriums = new HashSet<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public void setAuditoriums(Set<AuditoriumEntity> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public Long getId() {
        return id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public Set<AuditoriumEntity> getAuditoriums() {
        return auditoriums;
    }
}
