package ru.stqa.addressbook.model;

public record ContactData(
        String id,
        String firstname,

        String lastname,
        String address,
        String mobile,
        String home,
        String work,
        String secondary,
        String email,
        String email2,
        String email3) {

    public ContactData() {this("", "", "", "", "", "", "", "", "", "",  "");
    }
    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, email, this.email2, this.email3);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, home, this.work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, work, this.secondary, this.email, this.email2, this.email3);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, secondary, this.email, this.email2, this.email3);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, email2, this.email);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, email3);
    }




    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getId() {
        return this.id;
    }
}


