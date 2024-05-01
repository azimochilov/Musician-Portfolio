package Musician.Portfolio.Musician.Portfolio.api.test;

import Musician.Portfolio.Musician.Portfolio.domain.entities.contact.Contact;
import Musician.Portfolio.Musician.Portfolio.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tt")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {

    private final ContactService contactService;

    @Autowired
    public TestController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping("/")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact newContact = contactService.saveContact(contact);
        return ResponseEntity.ok(newContact);
    }

    @GetMapping("/")
    public ResponseEntity<String> add() {

        return ResponseEntity.ok("Done");
    }
}
