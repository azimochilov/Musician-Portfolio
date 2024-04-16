package Musician.Portfolio.Musician.Portfolio.repository.contact;

import Musician.Portfolio.Musician.Portfolio.domain.entities.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}