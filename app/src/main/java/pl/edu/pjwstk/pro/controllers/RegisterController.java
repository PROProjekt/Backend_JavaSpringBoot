package pl.edu.pjwstk.pro.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.RegisterRequest;
import pl.edu.pjwstk.pro.RoleService;
import pl.edu.pjwstk.pro.UserService;
import pl.edu.pjwstk.pro.email.EmailService;
import pl.edu.pjwstk.pro.entities.RoleEntity;
import pl.edu.pjwstk.pro.exceptions.UserAlreadyExistExcpetion;

import javax.mail.MessagingException;




@RestController
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {

        if(!userService.userExist(registerRequest.getEmail())){
            RoleEntity roleEntity;
            if(userService.isAnyUserExist()){
                roleEntity = roleService.getOrCreate("user");
            }else{
                roleEntity = roleService.getOrCreate("admin");
            }
            userService.saveUser(registerRequest.getEmail(),registerRequest.getFirstname(),
                    registerRequest.getLastname(),registerRequest.getPassword(),registerRequest.getBirth_date(),roleEntity);

            try{
                emailService.sendSuccessRegistrationEmail(registerRequest.getEmail());
            }catch (MessagingException e){
                e.printStackTrace();
            }
        }else{
            throw new UserAlreadyExistExcpetion();
        }
    }
}
