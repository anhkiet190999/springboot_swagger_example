package security.springframework.services;


import security.springframework.domain.Contact;


public interface ContactService {
    Iterable<Contact> listAllContacts();

    //Contact getContactById(Integer id);

    Contact addContact(Contact contact);

    Integer deleteContactByName(String name);
    Integer deleteContactByNumber(String number);
    boolean isExistedName(String name);
    boolean isExistedNumber(String number);
}
