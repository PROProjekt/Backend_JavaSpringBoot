package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.RegisterRequest;
import pl.edu.pjwstk.pro.RoleService;
import pl.edu.pjwstk.pro.UserService;
import pl.edu.pjwstk.pro.entities.RoleEntity;
import pl.edu.pjwstk.pro.entities.UserEntity;

import java.util.List;


@RestController
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {

        RoleEntity roleEntity;
        if(userService.isAnyUserExist()){
            roleEntity = roleService.getOrCreate("user");
        }else{
            roleEntity = roleService.getOrCreate("admin");
        }
        userService.saveUser(registerRequest.getUsername(),registerRequest.getPassword(),roleEntity);
    }

    @GetMapping("/auth0/forEveryone")
    public String thisShouldAllowedForEveryone(){
        return "ok";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/getUsers")
    public List<UserEntity> ReturnUsersList(){
        return userService.getUsers();
    }
}
