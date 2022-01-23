package pl.edu.pjwstk.pro.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.pro.User;
import pl.edu.pjwstk.pro.UserService;
import pl.edu.pjwstk.pro.responses.UserResponse;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    UserService service;

    public void sendSuccessRegistrationEmail(String email) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);
        helper.setSubject("Successful registration notification");
        helper.setText("<h1>Successful registration!</h1>", true);

        FileSystemResource file = new FileSystemResource(new File("app/src/main/java/pl/edu/pjwstk/pro/images/success.jpg"));

        helper.addAttachment("Success.jpg", file);

        javaMailSender.send(msg);
    }

    public void thanksForMakingOrder(String email,String movieTitle, String day, String time) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);
        helper.setSubject("Thanks for making order. Sorry! We're not connected to paypal yet.");
        helper.setText("<h2> You choose: "+movieTitle+" to "+day +" at "+ time+"</h2>", true);

        javaMailSender.send(msg);
    }
}
