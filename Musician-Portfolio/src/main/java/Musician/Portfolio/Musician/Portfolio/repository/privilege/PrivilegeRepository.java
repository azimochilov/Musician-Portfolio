package Musician.Portfolio.Musician.Portfolio.repository.privilege;


import Musician.Portfolio.Musician.Portfolio.domain.entities.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Optional<Privilege> findByName(String name);
}
