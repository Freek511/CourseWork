package freek.paintball_v1.Controller;

import freek.paintball_v1.DTO.CreateOrderRequest;
import freek.paintball_v1.Entity.Orders;
import freek.paintball_v1.Service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteOrderByAdmin(@PathVariable int id)
    {
        return ordersService.deleteOrderByAdmin(id);
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<Orders> getAllOrders(){
        return ordersService.getAll();
    }

    @GetMapping("/all")
    public Iterable<Orders> getAllOrdersForUser(@NonNull HttpServletRequest request){
        return ordersService.getAllOrdersForUser(request);
    }
}
