package freek.paintball_v1.Service;

import freek.paintball_v1.Config.JwtService;
import freek.paintball_v1.DTO.CreateOrderRequest;
import freek.paintball_v1.Entity.Orders;
import freek.paintball_v1.Entity.User;
import freek.paintball_v1.Repo.OrdersRepo;
import freek.paintball_v1.Repo.PlaygroundRepo;
import freek.paintball_v1.Repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final JwtService jwtService;
    private final OrdersRepo ordersRepo;
    private final PlaygroundRepo playgroundRepo;
    private final UserRepo userRepo;

    public ResponseEntity<String> createOrder(
            @NonNull HttpServletRequest request,
            CreateOrderRequest orderRequest
    ){
        final String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        String userEmail = jwtService.extractUsername(jwt);
        User user = userRepo.findFirstByEmail(userEmail);
        int playground_id = orderRequest.getPlayground_id();
        String date = orderRequest.getOrderDate();

        if(playgroundRepo.findById(playground_id).isEmpty()){
            return new ResponseEntity<>("Invalid playground", HttpStatusCode.valueOf(400));
        }

        Orders isExists = ordersRepo.findByDateAndPlaygroundId(date, playground_id);
        if(isExists != null){
            return new ResponseEntity<>("Order already exists", HttpStatusCode.valueOf(400));
        }

        Orders newOrder = new Orders();
        newOrder.setOrderDate(date);
        newOrder.setUser(user);
        newOrder.setPlayground(playgroundRepo.getReferenceById(playground_id));
        ordersRepo.saveAndFlush(newOrder);

        return new ResponseEntity<>("Order is created", HttpStatusCode.valueOf(201));
    }
    public ResponseEntity<String> deleteOrderByUser(HttpServletRequest request, int id){
        final String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        String userEmail = jwtService.extractUsername(jwt);
        User user = userRepo.findFirstByEmail(userEmail);
        if (user.getId() != ordersRepo.getUserIdById(id))
        {
            return new ResponseEntity<>("You are not owner of this order", HttpStatusCode.valueOf(400));
        }
        ordersRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted from your orders", HttpStatusCode.valueOf(200));
    }
    public Iterable<Orders> getAll(){
        return ordersRepo.findAll();
    }
    public Iterable<Orders> getAllOrdersForUser(){
        return null;
    }

    public boolean acceptOrder(){
        return true;
    }
}
