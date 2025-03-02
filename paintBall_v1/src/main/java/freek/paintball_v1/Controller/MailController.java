package freek.paintball_v1.Controller;

import freek.paintball_v1.Service.MyEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class MailController {

    private final MyEmailService emailService;

    @GetMapping("/{id}")
    public  ResponseEntity<String> sendSimpleEmail(@PathVariable int id) {

        try {
            emailService.sendSimpleEmail(id);
        } catch (MailException mailException) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }}
