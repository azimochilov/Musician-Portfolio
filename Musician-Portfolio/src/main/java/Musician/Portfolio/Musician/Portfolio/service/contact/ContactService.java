package Musician.Portfolio.Musician.Portfolio.service.contact;

import Musician.Portfolio.Musician.Portfolio.domain.entities.contact.Contact;
import Musician.Portfolio.Musician.Portfolio.repository.contact.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}

