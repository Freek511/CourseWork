package freek.paintball_v1.Service;

import freek.paintball_v1.Config.JwtService;
import freek.paintball_v1.DTO.CreateOrderRequest;
import freek.paintball_v1.DTO.OrderUpdateRequest;
import freek.paintball_v1.Entity.Orders;
import freek.paintball_v1.Entity.User;
import freek.paintball_v1.Repo.OrdersRepo;
import freek.paintball_v1.Repo.PlaygroundRepo;
import freek.paintball_v1.Repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final JwtService jwtService;
    private final OrdersRepo ordersRepo;
    private final PlaygroundRepo playgroundRepo;
    private final UserRepo userRepo;

    private User getUser(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        String userEmail = jwtService.extractUsername(jwt);
        return userRepo.findFirstByEmail(userEmail);
    }

    public ResponseEntity<String> createOrder(
            HttpServletRequest request,
            CreateOrderRequest orderRequest)
    {
        var user = getUser(request);
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
        var user = getUser(request);
        if (user.getId() != ordersRepo.getUserIdById(id))
        {
            return new ResponseEntity<>("You are not owner of this order", HttpStatusCode.valueOf(400));
        }
        ordersRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted from your orders", HttpStatusCode.valueOf(200));
    }
    public ResponseEntity<String> deleteOrderByAdmin(int id){
        ordersRepo.deleteById(id);
        return ResponseEntity.ok("Successfully deleted from all orders");
    }
    public List<Orders> getAll(){
        return ordersRepo.findAll();
    }
    public List<Orders> getAllOrdersForUser(HttpServletRequest request){
        var user = getUser(request);
        return ordersRepo.findAllByUserId(user.getId());
    }

    public ResponseEntity<String> updateOrderByAdmin(OrderUpdateRequest request){
        if(!ordersRepo.existsById(request.getId())){
            return new ResponseEntity<>(
                    "Error! Incorrect order id",
                    HttpStatusCode.valueOf(400));
        }
        Orders oldOrder = ordersRepo.getReferenceById(request.getId());
        if(ordersRepo.findByDateAndPlaygroundId(request.getOrderDate(), oldOrder.getPlayground().getId()) != null){
            return new ResponseEntity<>(
                    "Error! Order with this date already exits!",
                    HttpStatusCode.valueOf(400));
        }
        oldOrder.setOrderDate(request.getOrderDate());
        ordersRepo.saveAndFlush(oldOrder);
        return new ResponseEntity<>(
                "Your order date was updated",
                HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<String> updateOrderByUser(HttpServletRequest request,
                                                    OrderUpdateRequest orderUpdateRequest){
        var user = getUser(request);
        if(user.getId() != ordersRepo.getUserIdById(orderUpdateRequest.getId()) ){
            return new ResponseEntity<>(
                    "Error! You are not owner of this order",
                    HttpStatusCode.valueOf(400));
        }
        return updateOrderByAdmin(orderUpdateRequest);
    }

    public boolean acceptOrder(){
        return true;
    }
}
