package freek.paintball_v1;

import freek.paintball_v1.Controller.OrderController;
import freek.paintball_v1.DTO.CreateOrderRequest;
import freek.paintball_v1.DTO.OrderResponse;
import freek.paintball_v1.DTO.OrderUpdateRequest;
import freek.paintball_v1.Service.OrdersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrdersService ordersService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void getAllOrdersForUser() {
        // Mock data
        HttpServletRequest request = mock(HttpServletRequest.class);
        List<OrderResponse> orderResponses = Arrays.asList(new OrderResponse(), new OrderResponse());
        when(ordersService.getAllOrdersForUser(request)).thenReturn(orderResponses);

        // Call the controller method
        List<OrderResponse> result = orderController.getAllOrdersForUser(request);

        // Verify the result
        assertEquals(orderResponses, result);
        verify(ordersService, times(1)).getAllOrdersForUser(request);
    }

    @Test
    void getAllOrdersForAdmin() {
        // Mock data
        List<OrderResponse> orderResponses = Arrays.asList(new OrderResponse(), new OrderResponse());
        when(ordersService.getAll()).thenReturn(orderResponses);

        // Call the controller method
        List<OrderResponse> result = orderController.getAllOrders();

        // Verify the result
        assertEquals(orderResponses, result);
        verify(ordersService, times(1)).getAll();
    }

//    @Test
//    void updateOrderByAdmin() {
//        // Mock data
//        OrderUpdateRequest request = new OrderUpdateRequest();
//        when(ordersService.updateOrderByAdmin(request)).thenReturn(new ResponseEntity<>("Order updated successfully", HttpStatus.OK));
//
//        // Call the controller method
//        ResponseEntity<String> result = orderController.updateOrderByAdmin(request);
//
//        // Verify the result
//        assertEquals("Order updated successfully", result.getBody());
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        verify(ordersService, times(1)).updateOrderByAdmin(request);
//    }
    @Test
    void createOrder() {
        // Mock data
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(ordersService.createOrder(request, orderRequest)).thenReturn(new ResponseEntity<>("Order created successfully", HttpStatus.CREATED));

        // Call the controller method
        ResponseEntity<String> result = orderController.createOrder(request, orderRequest);

        // Verify the result
        assertEquals("Order created successfully", result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(ordersService, times(1)).createOrder(request, orderRequest);
    }

    @Test
    void deleteOrderByUser() {
        // Mock data
        int orderId = 1;
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(ordersService.deleteOrderByUser(request, orderId)).thenReturn(new ResponseEntity<>("Order deleted successfully", HttpStatus.OK));

        // Call the controller method
        ResponseEntity<String> result = orderController.deleteOrderByUser(orderId, request);

        // Verify the result
        assertEquals("Order deleted successfully", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(ordersService, times(1)).deleteOrderByUser(request, orderId);
    }


}
