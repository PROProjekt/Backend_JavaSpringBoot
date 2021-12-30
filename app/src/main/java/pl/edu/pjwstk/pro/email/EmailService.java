package pl.edu.pjwstk.pro.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSuccessRegistrationEmail(String email) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);
        helper.setSubject("Successful registration notification");
        helper.setText("<h1>Successful registration!</h1>", true);

        FileSystemResource file = new FileSystemResource(new File("app/src/main/java/pl/edu/pjwstk/pro/images/success.jpg"));
        //FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Lenovo E580\\Desktop\\PRO_Cinema\\app\\src\\main\\java\\pl\\edu\\pjwstk\\pro\\images\\success.jpg"));

        helper.addAttachment("Success.jpg", file);

        javaMailSender.send(msg);
    }
}
