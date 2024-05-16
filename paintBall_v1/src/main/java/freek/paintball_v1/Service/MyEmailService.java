package freek.paintball_v1.Service;
import freek.paintball_v1.DTO.OrderResponse;
import freek.paintball_v1.Entity.User;
import freek.paintball_v1.Repo.OrdersRepo;
import freek.paintball_v1.Repo.PlaygroundRepo;
import freek.paintball_v1.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyEmailService  {

    private final JavaMailSender mailSender;
    private final OrdersRepo ordersService;
    private final PlaygroundRepo playgroundRepo;
    private final UserRepo userRepo;


    public void sendSimpleEmail(int order_id)
    {
        var order = ordersService.getReferenceById(order_id);
        var user = userRepo.getReferenceById(order.getUser().getId());
        var playground = playgroundRepo.getReferenceById(order.getPlayground().getId());
        String toAddress = user.getEmail();
        String login = user.getLogin();

        String message = "Hello " + login + "! Your order with id: " + order_id + " successfully accepted! " +
                "We are waiting you on " + playground.getName() + " by " + order.getOrderDate();


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("pbtop.no.reply@yandex.ru");
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject("Order was accepted!");
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }

}
