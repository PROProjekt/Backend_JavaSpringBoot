package pl.edu.pjwstk.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.pro.RegisterRequest;
import pl.edu.pjwstk.pro.RoleService;
import pl.edu.pjwstk.pro.UserService;
import pl.edu.pjwstk.pro.entities.RoleEntity;
import pl.edu.pjwstk.pro.entities.UserEntity;
import pl.edu.pjwstk.pro.exceptions.UserAlreadyExistExcpetion;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;



@RestController
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {

        RoleEntity roleEntity;
        if(userService.isAnyUserExist()){
            roleEntity = roleService.getOrCreate("user");
        }else{
            roleEntity = roleService.getOrCreate("admin");
        }

        if(!userService.userExist(registerRequest.getEmail())){
            userService.saveUser(registerRequest.getEmail(),registerRequest.getFirstname(),registerRequest.getLastname(),registerRequest.getPassword(),registerRequest.getBirth_date(),roleEntity);

            try{
                sendEmail(registerRequest.getEmail());
            }catch (MessagingException e){
                e.printStackTrace();
            }
        }else{
            throw new UserAlreadyExistExcpetion();
        }

    }
    public void sendEmail(String email) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);

        helper.setSubject("Successful registration notification");

        helper.setText("<h1>Successful registration!</h1>", true);

        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Lenovo E580\\Desktop\\PRO_Cinema\\app\\src\\main\\java\\pl\\edu\\pjwstk\\pro\\images\\success.jpg"));

        helper.addAttachment("logo.jpg", file);

        javaMailSender.send(msg);

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
