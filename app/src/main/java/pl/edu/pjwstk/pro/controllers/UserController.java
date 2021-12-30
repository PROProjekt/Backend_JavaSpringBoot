package pl.edu.pjwstk.pro.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.pro.User;
import pl.edu.pjwstk.pro.UserService;


@RestController
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/getUserData")
    public String  getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = service.findByEmail(((User) principal).getEmail());
        String jsonString = new Gson().toJson(user);
        return jsonString;
    }
}

