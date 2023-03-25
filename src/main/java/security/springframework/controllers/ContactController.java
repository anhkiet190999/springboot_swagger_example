package security.springframework.controllers;

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

    private ContactService contactService;

    @Autowired
    public void setProductService(ContactService productService) {
        this.contactService = productService;
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
        return contactList;
    }

    @ApiOperation(value = "Add a contact")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity addContact(@RequestBody Contact contact){
        if(Validator.validateName(contact.getName()) == false){
            return new ResponseEntity("invalid input", HttpStatus.BAD_REQUEST); //400
        }

        contactService.addContact(contact);
        return new ResponseEntity("New contact saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a contact by name")
    @RequestMapping(value="/deleteByName/{name}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity deleteByName(@PathVariable String name){
        contactService.deleteContactByName(name);
        return new ResponseEntity("Contact deleted successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a contact by number")
    @RequestMapping(value="/deleteByNumber/{number}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity deleteByNumber(@PathVariable String number){
        contactService.deleteContactByNumber(number);
        return new ResponseEntity("Contact deleted successfully", HttpStatus.OK);
    }

}
