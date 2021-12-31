package pl.edu.pjwstk.pro.responses;

public class UserResponse {
    private String email;
    private String firstname;
    private String lastname;

    private String birth_date;

    public UserResponse(String email, String firstname, String lastname, String birth_date) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBirth_date() {
        return birth_date;
    }
}
