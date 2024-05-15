package freek.paintball_v1.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MyEmailService  {
    @Autowired
    public JavaMailSender mailSender;

    public void sendSimpleEmail(String toAddress, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("point.r-123@yandex.ru");
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }

}
