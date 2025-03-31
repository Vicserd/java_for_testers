package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("",
                    "firstname",
                    "lastname",
                    "",
                    "333",
                    "444",
                    "555",
                    "",
                    "mail",
                    "mail2",
                    "mail3",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        app.contact().openHomePage();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contactData ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"))
        ));

        var phones = app.contact().getPhones();
        Assertions.assertEquals(expected, phones);

        }


    @Test
    void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("",
                    "firstname",
                    "lastname",
                    "address",
                    "333",
                    "444",
                    "555",
                    "",
                    "mail",
                    "mail2",
                    "mail3",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""));
        }

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        app.contact().openHomePage();
        var address = app.contact().getAddress();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contactData ->
                Stream.of(contact.address())
                        .filter(a -> a != null && ! "".equals(a))
                        .map(a -> a.replace("\r\n", "\n"))
                        .collect(Collectors.joining("\n"))));


        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + address);

        Assertions.assertEquals(expected, address);

    }


    @Test
    void testEmails() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("",
                    "firstname",
                    "lastname",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "mail",
                    "mail2",
                    "mail3",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        app.contact().openHomePage();

        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contactData ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(e -> e != null && !"".equals(e))
                        .map(e-> e.replace("\r\n", "\n"))
                        .collect(Collectors.joining("\n"))
        ));
        var email = app.contact().getEmails();



        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + email);

        Assertions.assertEquals(expected, email);

    }

}
