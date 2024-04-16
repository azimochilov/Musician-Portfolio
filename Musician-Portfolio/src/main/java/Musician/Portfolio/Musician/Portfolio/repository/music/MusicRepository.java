package Musician.Portfolio.Musician.Portfolio.repository.music;

import Musician.Portfolio.Musician.Portfolio.domain.entities.music.Music;
import Musician.Portfolio.Musician.Portfolio.domain.entities.user.Privilege;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music,Long> {
    @EntityGraph(attributePaths = {"photo"})
    Optional<Music> findById(Long id);
}
