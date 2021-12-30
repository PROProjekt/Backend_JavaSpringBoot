package pl.edu.pjwstk.pro.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.AuthenticationService;
import pl.edu.pjwstk.pro.LoginRequest;
import pl.edu.pjwstk.pro.UserSession;
import pl.edu.pjwstk.pro.exceptions.UnauthorizedException;

@RestController
public class LoginController {
    private AuthenticationService authenticationService;
    private UserSession userSession;

    public LoginController(UserSession userSession, AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
        this.userSession = userSession;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest){
        var isLogged = authenticationService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (!isLogged) {
            throw new UnauthorizedException();
        }
        userSession.logIn();
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/forAdmin")
    public String thisIsForAdmin(){
        return "This is for Admin";
    }
}

