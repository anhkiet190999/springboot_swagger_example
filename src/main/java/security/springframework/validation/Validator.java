package security.springframework.validation;

import com.sun.prism.null3d.NULL3DPipeline;
import org.springframework.beans.factory.annotation.Autowired;
import security.springframework.domain.Contact;
import security.springframework.services.ContactService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private ContactService contactService;

    @Autowired
    public void setProductService(ContactService contactService) {
        this.contactService = contactService;
    }


//    public static Boolean isExistingName(String name){
//        Iterable<Contact> contactList = contactService.listAllContacts();
//    }

    public static Boolean validateName(String name){

        /*
        constrain:
            first name, middle name, last name are all single word/string
            each name could contain at most one apostrophe \'
            last name could be hyphenated by at most two different last names

            Acceptable inputs for name:
            • Bruce Schneier
            • Schneier, Bruce
            • Schneier, Bruce Wayne
            • O’Malley, John F.
            • John O’Malley-Smith
            • Cher
            - John F. Kenedy
            Unacceptable inputs for name:
            • Ron O’’Henry
            • Ron O’Henry-Smith-Barnes
            • L33t Hacker
            • <Script>alert(“XSS”)</Script>
            • Brad Everett Samuel Smith
            • select * from users;

            Allowing for <first middle last>, <first last> or <last, first MI>
            no comma case: ^[A-Za-z]+(\'[A-Za-z]+){0,1}(\ [A-Za-z]+(\'[A-Za-z]+){0,1}){0,1}(\ [A-Za-z]+(\'[A-Za-z]+){0,1}(\-[A-Za-z]+(\'[A-Za-z]+){0,1}){0,1}){0,1}$
            comma case: ^([A-Za-z]+(\'[A-Za-z]+){0,1}(\-[A-Za-z]+(\'[A-Za-z]+){0,1}){0,1})\,\ [A-Za-z]+(\'[A-Za-z]+){0,1}(\ (([A-Za-z]+(\'[A-Za-z]+){0,1})||([A-Za-z]\.))){0,1}$
         */
        String regex1 = "^[A-Za-z]+(\\'[A-Za-z]+){0,1}(\\ [A-Za-z]+(\\'[A-Za-z]+){0,1}){0,1}(\\ [A-Za-z]+(\\'[A-Za-z]+){0,1}(\\-[A-Za-z]+(\\'[A-Za-z]+){0,1}){0,1}){0,1}$";
        String regex2 = "^([A-Za-z]+(\\'[A-Za-z]+){0,1}(\\-[A-Za-z]+(\\'[A-Za-z]+){0,1}){0,1})\\,\\ [A-Za-z]+(\\'[A-Za-z]+){0,1}(\\ (([A-Za-z]+(\\'[A-Za-z]+){0,1})||([A-Za-z]\\.))){0,1}$";

        if(name.matches(regex1) || name.matches(regex2)){
            return true;
        }
        return false;
    }

    public static Boolean validateNumber(String number){

        return true;
    }
}
