package freek.paintball_v1.Repo;

import freek.paintball_v1.Entity.Playground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaygroundRepo extends JpaRepository<Playground, Integer> {
}
