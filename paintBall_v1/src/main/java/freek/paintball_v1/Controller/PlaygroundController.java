package freek.paintball_v1.Controller;

import freek.paintball_v1.DTO.PlaygroundUpdateRequest;
import freek.paintball_v1.Entity.Playground;
import freek.paintball_v1.Service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/playgrounds")
@RequiredArgsConstructor
public class PlaygroundController {
    private final PlaygroundService playgroundService;


    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Iterable<Playground> getAllPlaygrounds(){
        return playgroundService.getAllPlaygrounds();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Object getPlaygroundById(@PathVariable int id){
        return playgroundService.getPlaygroundById(id);
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createPlayground(@RequestBody Playground playground){
       return playgroundService.createPlayground(playground);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updatePlayground(@PathVariable int id, @RequestBody PlaygroundUpdateRequest updateRequest){
        return playgroundService.updatePlayground(id, updateRequest);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deletePlayground(@PathVariable int id){
       return playgroundService.deletePlayground(id);
    }

}
