package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "contact firstname")) {
//            for (var lastname : List.of("", "contact lastname")) {
//                for (var address : List.of("", "contact address")) {
//                    for (var mobile : List.of("", "contact mobile")) {
//                        for (var email : List.of("", "contact@email")) {
//                            result.add(new ContactData()
//                                    .withFirstname(firstname)
//                                    .withLastname(lastname)
//                                    .withAddress(address)
//                                    .withMobile(mobile)
//                                    .withEmail(email));
//                        }
//                    }
//
//                }
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            result.add(new ContactData()
//                    .withFirstname(CommonFunctions.randomString(i * 10))
//                    .withLastname(CommonFunctions.randomString(i * 10))
//                    .withAddress(CommonFunctions.randomString(i * 10))
//                    .withMobile(CommonFunctions.randomString(i * 10))
//                    .withEmail(CommonFunctions.randomString(i * 10)));
//
//        }
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;

    }

@ParameterizedTest
@MethodSource("contactProvider")
public void canCreateMultipleContact(ContactData contact) {
    // Получаем старый список контактов
    var oldContacts = app.contact().getListContacts();

    // Добавляем новый контакт
    app.contact().createContact(contact);

    // Получаем новый список контактов
    var newContacts = app.contact().getListContacts();

    // Сравнение по id
    Comparator<ContactData> compareById = (o1, o2) -> Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));

    // Сортируем оба списка
    newContacts.sort(compareById);

    // Создаем ожидаемый список
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
            .withAddress("").withEmail("").withMobile("").withPhoto(""));
    expectedList.sort(compareById);

    // Сравниваем старый и новый список
    Assertions.assertEquals(expectedList, newContacts);
}

@Test
    void canCreateContact(){
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contact().createContact(contact);
}

}
