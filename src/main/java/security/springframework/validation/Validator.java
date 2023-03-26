package security.springframework.validation;


import org.springframework.beans.factory.annotation.Autowired;
import security.springframework.domain.Contact;
import security.springframework.services.ContactService;

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
        /*
        country code rule: if using the '+' then country code MUST be included, else country code is optional
            +1 682 772 0172
            1 682 772 0172
            682 772 0271
        country code can have 1, 2, or 3 digit

        internalPhone_regex: "^\\d{5}$";
        <Subscriber Number> (e.g. 123-4567): ^(\d){3}-(\d){4}$
        o (<Area Code>)<Subscriber Number> (e.g. (670)123-4567): ^\((\d){2,3}\)(\d){3}-(\d){4}$
        o <Area Code>-<Subscriber Number> (e.g. 670-123-4567): ^(\d){2,3}-(\d){3}-(\d){4}$
        o 1-<Area Code>-<Subscriber Number> (e.g. 1-670-123-4567): "^(\+?(\d){1,3})\ (\d){2,3}-(\d){3}-(\d){4}$"
        o 1(<Area Code>)<Subscriber Number> (e.g. 1(670)123-4567): ^(\+?(\d){1,3})\((\d){2,3}\)(\d){3}-(\d){4}$
        o <Area Code> <Subscriber Number> (e.g. 670 123 4567): "^(\d){2,3}\ (\d){3}\ (\d){4}$"
        o <Area Code>.<Subscriber Number> (e.g. 670.123.4567): "^(\d){2,3}\.(\d){3}\.(\d){4}$"
        o 1 <Area Code> <Subscriber Number> (e.g. 1 670 123 4567): "^(\+?(\d){1,3})\ (\d){2,3}\ (\d){3}\ (\d){4}"
        o 1.<Area Code>.<Subscriber Number> (e.g. 1.670.123.4567): "^(\+?(\d){1,3})\.(\d){2,3}\.(\d){3}\.(\d){4}"
        1 <area code> <3>-<4>: "^(\+?(\d){1,3}) (\d){2,3} (\d){3}\-(\d){4}"
        "^(\+?(\d){1,3}) \((\d){2,3}\) (\d){3}\-(\d){4}"
        "^(\d){5}.(\d){5}$"
        */
        String regex = "^(\\d{5})" +
                "|((\\d){3}-(\\d){4})" +
                "|(\\((\\d){2,3}\\)(\\d){3}-(\\d){4})" +
                "|((\\d){2,3}-(\\d){3}-(\\d){4})" +
                "|((\\+?(\\d){1,3})\\ (\\d){2,3}-(\\d){3}-(\\d){4})" +
                "|((\\+?(\\d){1,3})\\((\\d){2,3}\\)(\\d){3}-(\\d){4})" +
                "|((\\d){2,3}\\ (\\d){3}\\ (\\d){4})" +
                "|((\\d){2,3}\\.(\\d){3}\\.(\\d){4})" +
                "|((\\+?(\\d){1,3})\\ (\\d){2,3}\\ (\\d){3}\\ (\\d){4})" +
                "|((\\+?(\\d){1,3})\\.(\\d){2,3}\\.(\\d){3}\\.(\\d){4})" +
                "|(^(\\+?(\\d){1,3}) (\\d){2,3} (\\d){3}\\-(\\d){4})" +
                "|(^(\\+?(\\d){1,3}) \\((\\d){2,3}\\) (\\d){3}\\-(\\d){4})+" +
                "|((\\d){5}[\\ \\.](\\d){5})" +
                "|((\\d){4}[\\ \\.](\\d){4})" +
                "|((\\d){2}\\ (\\d){2}\\ (\\d){2}\\ (\\d){2})$";
        if(number.matches(regex)){
            return true;
        }
        return false;
    }
}
