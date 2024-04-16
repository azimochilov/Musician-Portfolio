package Musician.Portfolio.Musician.Portfolio.repository.user;


import Musician.Portfolio.Musician.Portfolio.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByUserId(Long id);
    User getByUsername(String username);

}
