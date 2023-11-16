package freek.paintball_v1.Repo;

import freek.paintball_v1.Entity.Playground;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlaygroundRepo extends JpaRepository<Playground, Integer> {
    Optional <Playground> findByName(String name);
}
