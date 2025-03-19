package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.contact().createContact(new ContactData("", "firstname", "lastname",
                    "address", "mobile", "email", "", "", ""));
        }
        var oldContacts = app.contact().getListContacts();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contact().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.contact().createContact(new ContactData("", "firstname", "lastname",
                    "address", "mobile", "email", "", "", ""));
        }
        app.contact().removeAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

    @Test
    void canRemoveContactInGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.groups().createGroup(new GroupData("",
                    "group name",
                    "group header",
                    "group footer"));
        }

        var group = app.hbm().getGroupList().get(0);
        var contactsInGroup = app.hbm().getContactInGroup(group);
        ContactData contact;

        if (!contactsInGroup.isEmpty()) {
            contact = contactsInGroup.get(0);
        } else {
            contact = new ContactData()
                    .withFirstname(CommonFunctions.randomString(10))
                    .withLastname(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10));

            app.contact().createContact(contact, group);
            contactsInGroup = app.hbm().getContactInGroup(group);
            Assertions.assertTrue(contactsInGroup.contains(contact), "Контакт не добавился в группу!");
        }


    }
}
