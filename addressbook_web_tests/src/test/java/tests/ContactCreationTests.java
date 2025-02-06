package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

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
}
