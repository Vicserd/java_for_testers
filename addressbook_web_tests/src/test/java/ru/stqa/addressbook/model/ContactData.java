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
        String email3,
        String middlename,
        String nickname,
        String company,
        String title,
        String photo,
        String fax,
        String homepage) {

    public ContactData() {this("",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "");
    }
    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax, this.homepage);
    }
    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, email2, this.email, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withMiddlename(String middlename) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, middlename, this.nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withNickname(String nickname) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, nickname, this.company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, company, this.title, this.photo, this.fax,this.homepage);
    }
    public ContactData withTitle(String title) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, title, this.photo, this.fax,this.homepage);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, photo, this.fax,this.homepage);
    }
    public ContactData withFax(String fax) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, fax,this.homepage);
    }
    public ContactData withHomepage(String homepage) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3, this.middlename, this.nickname, this.company, this.title, this.photo, this.fax,homepage);
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


