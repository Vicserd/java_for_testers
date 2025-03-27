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
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"))
        ));

        var phones = app.contact().getPhones();
        Assertions.assertEquals(expected, phones);

        }

//    @Test
//    void testAddress() {
//        if (app.hbm().getContactCount() == 0) {
//            app.hbm().createContact(new ContactData("", "firstname", "lastname","address", "", "", "", "", ""));
//        }
//
//        var contacts = app.hbm().getContactList();
//        var contact = contacts.get(0);
//        var address = app.contact().getAddress();
//      var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contactData ->
//               Stream.of(contact.address())
//              .filter(s -> s != null && ! "".equals(s))
//              .collect(Collectors.joining("\r\n"))));
//
//        Assertions.assertEquals(expected, address);
//
//    }

}
