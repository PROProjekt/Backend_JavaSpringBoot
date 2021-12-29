package pl.edu.pjwstk.pro;

import java.util.Set;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Set<String> authorities;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }
    //    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//    public void setAuthorities(Set<String> authorities) {
//        this.authorities = authorities;
//    }
//
//    public Set<String> getAuthorities() {
//        return authorities;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }

}
