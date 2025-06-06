package ru.stqa.addressbook.manager;

import io.qameta.allure.Step;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;


    public HibernateHelper(ApplicationManager manager) {

        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)

                //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)

                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                // Credentials
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")

                // Create a new SessionFactory
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());

    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());

    }

    private static ContactData convert(ContactRecord record){
        return new ContactData().withId("" + record.id)
                .withFirstname(record.firstname)
                .withLastname(record.lastname)
                .withAddress(record.address)
                .withHome(record.home)
                .withMobile(record.mobile)
                .withWork(record.work)
                .withSecondary(record.phone2)
                .withMiddlename(record.middlename)
                .withNickname(record.nickname)
                .withCompany(record.company)
                .withTitle(record.title)
                .withPhoto(record.photo)
                .withFax(record.fax)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withHomepage(record.homepage);
    }

    private static ContactRecord convert (ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id),
                data.firstname(),
                data.lastname(),
                data.address(),
                data.company(),
                data.email(),
                data.email2(),
                data.email3(),
                data.fax(),
                data.middlename(),
                data.mobile(),
                data.home(),
                data.work(),
                data.secondary(),
                data.nickname(),
                data.title(),
                data.photo(),
                data.homepage()
        );
    }
@Step
    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

@Step
    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);

        });


    }

    public List<ContactData> getContactList() {
        return (sessionFactory.fromSession(session -> {
            return convertContactList(session.createQuery("from ContactRecord", ContactRecord.class).list());
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }

}

