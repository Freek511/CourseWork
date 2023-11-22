package freek.paintball_v1;

import freek.paintball_v1.Entity.Playground;
import freek.paintball_v1.Repo.PlaygroundRepo;
import freek.paintball_v1.Service.PlaygroundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaygroundServiceTest {

    @Mock
    private PlaygroundRepo playgroundRepo;

    @InjectMocks
    private PlaygroundService playgroundService;

    @Test
    void getAllPlaygrounds() {
        // Mock data
        Playground playground1 = new Playground();
        Playground playground2 = new Playground();
        when(playgroundRepo.findAll()).thenReturn(Arrays.asList(playground1, playground2));

        // Call the service method
        Iterable<Playground> result = playgroundService.getAllPlaygrounds();

        // Verify the result
        assertEquals(Arrays.asList(playground1, playground2), result);
        verify(playgroundRepo, times(1)).findAll();
    }

    @Test
    void createPlayground() {
        // Mock data
        Playground playground = new Playground();
        when(playgroundRepo.findByName(playground.getName())).thenReturn(Optional.empty());

        // Call the service method
        ResponseEntity<String> result = playgroundService.createPlayground(playground);

        // Verify the result
        assertEquals("Successfully added", result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(playgroundRepo, times(1)).saveAndFlush(playground);
    }

    @Test
    void createPlaygroundAlreadyExists() {
        // Mock data
        Playground playground = new Playground();
        when(playgroundRepo.findByName(playground.getName())).thenReturn(Optional.of(playground));

        // Call the service method
        ResponseEntity<String> result = playgroundService.createPlayground(playground);

        // Verify the result
        assertEquals("Playground already exists", result.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(playgroundRepo, never()).saveAndFlush(playground);
    }

    // Similar tests can be written for other methods in PlaygroundService

    // ...

}
