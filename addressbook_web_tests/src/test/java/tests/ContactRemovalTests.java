package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact(){
        if (!app.contact().isContactPresent()){
            app.contact().createContact(new ContactData("", "firstname", "lastname",
                    "address", "mobile", "email"));
        }
        var oldContacts = app.contact().getListContacts();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());  //Не могу понять почему ругается на отрицательные значения
        app.contact().removeContact(oldContacts.get(index));
        var newContacts = app.contact().getListContacts();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }
    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.contact().getCount() == 0) {
            app.contact().createContact(new ContactData("", "firstname", "lastname",
                    "address", "mobile", "email"));
        }
        app.contact().removeAllContacts();
        Assertions.assertEquals(0, app.contact().getCount());
    }

}
