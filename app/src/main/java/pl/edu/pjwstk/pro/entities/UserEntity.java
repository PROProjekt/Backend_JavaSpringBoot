package pl.edu.pjwstk.pro.entities;

import pl.edu.pjwstk.pro.entities.RoleEntity;

import javax.persistence.*;


@Entity
@Table(name = "user", schema = "public")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity authority;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;


    public void setUsername(String nick) {
        this.username = nick;
    }

    public RoleEntity getAuthority() {
        return authority;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setAuthority(RoleEntity authority) {
        this.authority = authority;
    }
}

