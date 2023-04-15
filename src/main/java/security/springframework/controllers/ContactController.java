package security.springframework.controllers;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import security.springframework.domain.Contact;
import security.springframework.services.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import security.springframework.validation.Validator;

@RestController
@RequestMapping("/PhoneBook")
@Api(value="phonebook", description="Operations with contacts in a PhoneBook")
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @ApiOperation(value = "View a list of all contacts in the phonebook",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "valid input - successfully add new contact"),
            @ApiResponse(code = 400, message = "invalid input"),
            @ApiResponse(code = 404, message = "non-existing name/number")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Contact> list(Model model){
        Iterable<Contact> contactList = contactService.listAllContacts();
        logger.info("/list");
        return contactList;
    }

    @ApiOperation(value = "Add a contact")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addContact(@RequestBody Contact contact){
        if(Validator.validateName(contact.getName()) == false){
            return new ResponseEntity("invalid name", HttpStatus.BAD_REQUEST); //400
        }
        if(contactService.isExistedName(contact.getName())==true){
            return new ResponseEntity("name already existed", HttpStatus.BAD_REQUEST); //400
        }
        if(Validator.validateNumber(contact.getNumber())==false){
            return new ResponseEntity("invalid number", HttpStatus.BAD_REQUEST); //400
        }
        if(contactService.isExistedNumber(contact.getNumber())==true){
            return new ResponseEntity("number already existed", HttpStatus.BAD_REQUEST); //400
        }
        contactService.addContact(contact);
        return new ResponseEntity("New contact saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a contact by name")
    @RequestMapping(value="/deleteByName/{name}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity deleteByName(@PathVariable String name){
        if(contactService.deleteContactByName(name) == -1){
            return new ResponseEntity("non-existent name", HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity("Contact deleted successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a contact by number")
    @RequestMapping(value="/deleteByNumber/{number}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity deleteByNumber(@PathVariable String number){
        if(contactService.deleteContactByNumber(number) == -1){
            return new ResponseEntity("non-existent number", HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity("Contact deleted successfully", HttpStatus.OK);
    }

}
