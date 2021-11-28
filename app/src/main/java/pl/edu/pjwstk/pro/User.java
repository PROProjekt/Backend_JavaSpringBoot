package pl.edu.pjwstk.pro;

import java.util.Set;

public class User {
    private String username;
    private String password;
    private Set<String> authorities;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
