package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
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
    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.contact().getCount() == 0) {
            app.contact().createContact(new ContactData("firstname", "lastname",
                    "address", "mobile", "email"));
        }
        app.contact().removeAllContacts();
        Assertions.assertEquals(0, app.contact().getCount());
    }
/*
    @Test
    void canRemoveAllContactsSelectAll() {
        if (app.contact().getCount() == 0) {
            app.contact().createContact(new ContactData("firstname", "lastname",
                    "address", "mobile", "email"));
        }
        app.contact().selectAllContacts();
        Assertions.assertEquals(0, app.contact().getCount());
    }

 */

}
