package security.springframework.services;


import security.springframework.domain.Contact;


public interface ContactService {
    Iterable<Contact> listAllContacts();

    //Contact getContactById(Integer id);

    Contact addContact(Contact contact);

    void deleteContactByName(String name);
    void deleteContactByNumber(String number);
}
