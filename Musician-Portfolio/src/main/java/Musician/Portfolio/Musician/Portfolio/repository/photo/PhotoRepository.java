package Musician.Portfolio.Musician.Portfolio.repository.photo;

import Musician.Portfolio.Musician.Portfolio.domain.entities.music.Music;
import Musician.Portfolio.Musician.Portfolio.domain.entities.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
