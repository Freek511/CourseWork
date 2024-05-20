package freek.paintball_v1.Controller;

import freek.paintball_v1.DTO.CreateOrderRequest;
import freek.paintball_v1.DTO.OrderResponse;
import freek.paintball_v1.DTO.OrderUpdateRequest;
import freek.paintball_v1.Service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrdersService ordersService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(
            @NonNull HttpServletRequest request,
            @RequestBody CreateOrderRequest orderRequest)
    {
        return ordersService.createOrder(request, orderRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderByUser(
            @PathVariable int id,
            @NonNull HttpServletRequest request
            )
    {
        return ordersService.deleteOrderByUser(request, id);
    }

    @DeleteMapping("/delete-hard/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<String> deleteOrderByAdmin(@PathVariable int id)
    {
        return ordersService.deleteOrderByAdmin(id);
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderResponse> getAllOrders(){
        return ordersService.getAll();
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrdersForUser(@NonNull HttpServletRequest request){
        return ordersService.getAllOrdersForUser(request);
    }
    @GetMapping("/{id}")
    public OrderResponse getAllOrdersForUser(@PathVariable int id){
        return ordersService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@RequestBody OrderUpdateRequest updateRequest,
                                                     @PathVariable int id){
        return ordersService.updateOrder(updateRequest, id);
    }
}
