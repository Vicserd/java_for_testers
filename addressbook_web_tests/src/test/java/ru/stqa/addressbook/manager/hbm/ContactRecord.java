package ru.stqa.addressbook.manager.hbm;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    @Column (name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "lastname")
    public String lastname;
    @Column(name = "address")
    public String address;
    @Column(name = "home")
    public String home;
    @Column(name = "mobile")
    public String mobile;
    @Column(name = "work")
    public String work;
    public String phone2;
    public String middlename;
    public String nickname;
    public String company;
    public String title;
    public String photo;
    public String fax;
    @Column(name = "email")
    public String email;
    @Column(name = "email2")
    public String email2;
    @Column(name = "email3")
    public String email3;
    public String homepage;



    public ContactRecord() {

    }

    public ContactRecord (int id,String firstname, String lastname, String address, String company, String email,
                          String email2, String email3, String fax, String middlename, String mobile,
                          String home, String work, String secondary, String nickname, String title, String photo,
                          String homepage) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.company = company;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.fax = fax;
        this.middlename = middlename;
        this.mobile = mobile;
        this.home = home;
        this.work = work;
        this.nickname = nickname;
        this.title = title;
        this.photo = photo;
        this.homepage = homepage;

    }
}
