package hbv501g.Persistence.Repositories;

import hbv501g.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
