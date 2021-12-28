package pl.edu.pjwstk.pro.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "screening", schema = "public")
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="day")
    private Date date;
    @Column(name="time")
    private String time;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
