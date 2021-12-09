package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;

@Entity
@Table(name = "auditorium",schema = "public")
public class AuditoriumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="seat_number")
    private String seat_number;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private ScreeningEntity screening;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public void setScreening(ScreeningEntity screening) {
        this.screening = screening;
    }

    public Long getId() {
        return id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public ScreeningEntity getScreening() {
        return screening;
    }
}
