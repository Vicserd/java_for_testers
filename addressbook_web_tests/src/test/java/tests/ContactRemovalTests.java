package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact(){
        if (!app.contact().isContactPresent()){
            app.contact().createContact(new ContactData("firstname", "lastname",
                    "address", "mobile", "email"));
        }

        app.contact().removeContact();
    }
}
