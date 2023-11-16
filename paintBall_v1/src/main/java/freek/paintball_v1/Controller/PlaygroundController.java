package freek.paintball_v1.Controller;

import freek.paintball_v1.DAO.PlaygroundUpdateRequest;
import freek.paintball_v1.Entity.Playground;
import freek.paintball_v1.Repo.PlaygroundRepo;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/playgrounds")
public class PlaygroundController {
    private final PlaygroundRepo playgroundRepo;

    public PlaygroundController(PlaygroundRepo playgroundRepo) {
        this.playgroundRepo = playgroundRepo;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Iterable<Playground> getAllPlaygrounds(){
        return playgroundRepo.findAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Object getPlaygroundById(@PathVariable int id){
        if(playgroundRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>("Invalid id", HttpStatusCode.valueOf(400));
        }
        return playgroundRepo.findById(id).get();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> createPlayground(@RequestBody Playground playground){
        if(playgroundRepo.findByName(playground.getName()).isPresent()) {
            return new ResponseEntity<>("Playground already exists", HttpStatusCode.valueOf(400));
        }
        playgroundRepo.saveAndFlush(playground);
        return new ResponseEntity<>("Successfully added", HttpStatusCode.valueOf(201));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updatePlayground(@RequestBody PlaygroundUpdateRequest updateRequest){

        if(!playgroundRepo.existsById(updateRequest.getId())) {
            return new ResponseEntity<>("No such element in database", HttpStatusCode.valueOf(400));
        }
        Playground oldPlayground = playgroundRepo.getReferenceById(updateRequest.getId());
        oldPlayground.setArea(updateRequest.getArea());
        oldPlayground.setDescription(updateRequest.getDescription());
        oldPlayground.setCapacity(updateRequest.getCapacity());
        oldPlayground.setPrice(updateRequest.getPrice());

        playgroundRepo.saveAndFlush(oldPlayground);
        return new ResponseEntity<>("Successfully updated", HttpStatusCode.valueOf(200));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deletePlayground(@PathVariable int id){
        if(!playgroundRepo.existsById(id))
        {
            return new ResponseEntity<>("No such element in database", HttpStatusCode.valueOf(400));
        }
        playgroundRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatusCode.valueOf(200));
    }

}
