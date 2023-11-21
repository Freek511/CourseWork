package freek.paintball_v1.Service;

import freek.paintball_v1.DTO.PlaygroundUpdateRequest;
import freek.paintball_v1.Entity.Playground;
import freek.paintball_v1.Repo.PlaygroundRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaygroundService {
    private final PlaygroundRepo playgroundRepo;

    public Iterable<Playground> getAllPlaygrounds(){
        return playgroundRepo.findAll();
    }
    public Object getPlaygroundById(int id){
        if(playgroundRepo.findById(id).isEmpty()) {
            return new ResponseEntity<>("Invalid id", HttpStatusCode.valueOf(400));
        }
        return playgroundRepo.findById(id).get();
    }
    public ResponseEntity<String> createPlayground(Playground playground){
        if(playgroundRepo.findByName(playground.getName()).isPresent()) {
            return new ResponseEntity<>("Playground already exists", HttpStatusCode.valueOf(400));
        }
        playgroundRepo.saveAndFlush(playground);
        return new ResponseEntity<>("Successfully added", HttpStatusCode.valueOf(201));
    }

    public ResponseEntity<String> updatePlayground( PlaygroundUpdateRequest updateRequest){

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
    public ResponseEntity<String> deletePlayground(int id){
        if(!playgroundRepo.existsById(id))
        {
            return new ResponseEntity<>("No such element in database", HttpStatusCode.valueOf(400));
        }
        playgroundRepo.deleteById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatusCode.valueOf(200));
    }

}
