package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase{



    @Test
    public void canCreateContact(){
        app.contact().createContact(new ContactData("firstname", "lastname",
                "address", "mobile", "email"));

    }
    @Test
    public void canNewPageAddContact(){
        app.contact().createContactAndNewPagAddContact(new ContactData("firstname", "lastname",
                "address", "mobile", "email_add_new_contact"));

    }


    @Test
    public void canCreateContactWithEmptyName() {
        app.contact().createContact(new ContactData());
    }
    @Test
    public void canCreateContactWithLastNameOnly(){
        app.contact().createContact(new ContactData().withLastname("some lastname"));
    }

    @Test
    public void canCreateContactWithFirstNameOnly(){
        app.contact().createContact(new ContactData().withFirstname("some firstname"));
    }
    @Test
    public void canCreateContactWithAddressOnly(){
        app.contact().createContact(new ContactData().withAddress("some address"));
    }
    @Test
    public void canCreateContactWithMobileOnly(){
        app.contact().createContact(new ContactData().withMobile("some mobile123"));
    }
    @Test
    public void canCreateContactWithEmail(){
        app.contact().createContact(new ContactData().withEmail("some email"));
    }

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "contact firstname")){
            for (var lastname : List.of("", "contact lastname")){
                for (var address : List.of("", "contact address")){
                    for (var mobile : List.of("","contact mobile")){
                        for (var email : List.of("", "contact@email")){
                            result.add(new ContactData(firstname, lastname, address, mobile, email));
                        }
                    }

                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new ContactData(randomString(i*10), randomString(i*10),
                    randomString(i*10), randomString(i*10), randomString(i*10)));
        }
        return result;

    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        int contactCount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactCount = app.contact().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

}
