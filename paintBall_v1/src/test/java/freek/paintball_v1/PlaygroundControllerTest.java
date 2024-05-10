package freek.paintball_v1;

import freek.paintball_v1.Controller.PlaygroundController;
import freek.paintball_v1.DTO.PlaygroundUpdateRequest;
import freek.paintball_v1.Entity.Playground;
import freek.paintball_v1.Service.PlaygroundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaygroundControllerTest {

    @Mock
    private PlaygroundService playgroundService;

    @InjectMocks
    private PlaygroundController playgroundController;


    @Test
    void getAllPlaygrounds() {
        // Mock data
        Playground playground1 = new Playground();
        Playground playground2 = new Playground();
        when(playgroundService.getAllPlaygrounds()).thenReturn(Arrays.asList(playground1, playground2));

        // Call the controller method
        Iterable<Playground> result = playgroundController.getAllPlaygrounds();

        // Verify the result
        assertEquals(Arrays.asList(playground1, playground2), result);
        verify(playgroundService, times(1)).getAllPlaygrounds();
    }

    @Test
    void getPlaygroundById() {
        // Mock data
        int playgroundId = 1;
        Playground playground = new Playground();
        when(playgroundService.getPlaygroundById(playgroundId)).thenReturn(playground);

        // Call the controller method
        Object result = playgroundController.getPlaygroundById(playgroundId);

        // Verify the result
        assertEquals(playground, result);
        verify(playgroundService, times(1)).getPlaygroundById(playgroundId);
    }

    @Test
    void createPlayground() {
        // Mock data
        Playground playground = new Playground();
        when(playgroundService.createPlayground(playground))
                .thenReturn(new ResponseEntity<>("Playground created successfully", HttpStatus.CREATED));

        // Call the controller method
        ResponseEntity<String> result = playgroundController.createPlayground(playground);

        // Verify the result
        assertEquals("Playground created successfully", result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(playgroundService, times(1)).createPlayground(playground);
    }


    @Test
    void deletePlayground() {
        // Mock data
        int playgroundId = 1;
        when(playgroundService.deletePlayground(playgroundId)).thenReturn(new ResponseEntity<>("Playground deleted successfully", HttpStatus.OK));

        // Call the controller method
        ResponseEntity<String> result = playgroundController.deletePlayground(playgroundId);

        // Verify the result
        assertEquals("Playground deleted successfully", result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(playgroundService, times(1)).deletePlayground(playgroundId);
    }
}
