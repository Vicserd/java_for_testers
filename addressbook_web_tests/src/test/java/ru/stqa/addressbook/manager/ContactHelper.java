package ru.stqa.addressbook.manager;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("home"));
        }
    }


    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }


    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnHomePage();

    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void createContactAndNewPagAddContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnAddNextContactPage();

    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();

    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("lastname"), contact.lastname());
        type(By.name("firstname"), contact.firstname());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("mobile"), contact.mobile());
        //attach(By.name("photo"), contact.photo());


    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnHomePage() {
        click(By.linkText("home page"));
    }

    private void returnAddNextContactPage() {
        click(By.linkText("add next"));
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContact();

    }

    private void selectAllContacts() {
        var checkBoxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkBoxes) {
            checkbox.click();
        }
    }


    public List<ContactData> getListContacts() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var firstname = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastname = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();

            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactUpdate();
        openHomePage();
    }

    private void submitContactUpdate() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        var pencil = String.format("//td[@class='center']/a[@href='edit.php?id=%s']", contact.id());
        click(By.xpath(pencil));


    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());

        selectContact(contact);
        removeFrom();
        openHomePage();


    }

    private void removeFrom() {
        manager.driver.findElement(By.name("remove")).click();
    }


    private void selectGroupSort(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        WebElement checkbox = manager.driver.findElement(By.cssSelector(String.format("input[value='%s']", contact.id())));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        selectGroupSort(group);
        click(By.name("add"));
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;

    }

    public Map<String, String> getAddress() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, address);
        }
        return result;

    }
    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var email = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, email);
        }
        return result;
    }



    public void removeContactAndVerify(ContactData contact, GroupData group) {
        removeContactFromGroup(contact, group);  // Удаляем контакт из группы
        var contactsInGroup = manager.hbm().getContactInGroup(group);
        Assertions.assertFalse(contactsInGroup.contains(contact), "Контакт не удалился из группы!");
    }


}