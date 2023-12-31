package freek.paintball_v1.Repo;

import freek.paintball_v1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findFirstByEmail(String email);
}
