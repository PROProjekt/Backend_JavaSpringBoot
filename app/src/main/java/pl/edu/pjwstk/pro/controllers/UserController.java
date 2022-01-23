package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.pro.User;
import pl.edu.pjwstk.pro.responses.UserResponse;
import pl.edu.pjwstk.pro.UserService;


@RestController
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/getUserData")
    public ResponseEntity<UserResponse> getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = service.findByEmail(((User) principal).getEmail());
        var user_response = new UserResponse(user.getEmail(), user.getFirstname(), user.getLastname(), user.getBirth_date());
        return ResponseEntity.ok(user_response);
    }


}

