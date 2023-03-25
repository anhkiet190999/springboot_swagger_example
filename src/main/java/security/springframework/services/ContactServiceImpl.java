package security.springframework.services;

import security.springframework.repositories.ContactRepository;
import security.springframework.domain.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Iterable<Contact> listAllContacts() {
        logger.debug("listAllContacts called");
        return contactRepository.findAll();
    }

//    @Override
//    public Contact getContactById(Integer id) {
//        logger.debug("getContactByName called");
//        return contactRepository.findById(id).orElse(null);
//    }

    @Override
    public Contact addContact(Contact contact) {
        logger.debug("addContact called");
        return contactRepository.save(contact);
    }

    private  Integer findIdByName(String name){
        Iterable<Contact> phonebook = contactRepository.findAll();
        for(Contact contact : phonebook){
            if(contact.getName().equals(name)){
                return contact.getId();
            }
        }
        return 0;
    }

    @Override
    public void deleteContactByName(String name) {
        logger.debug("deleteContactByName called");
        Integer id = findIdByName(name);
        contactRepository.deleteById(id);
    }

    private  Integer findIdByNumber(String number){
        Iterable<Contact> phonebook = contactRepository.findAll();
        for(Contact contact : phonebook){
            if(contact.getNumber().equals(number)){
                return contact.getId();
            }
        }
        return 0;
    }

    @Override
    public void deleteContactByNumber(String number) {
        logger.debug("deleteContactByNumber called");
        Integer id = findIdByNumber(number);
        contactRepository.deleteById(id);
    }
}
