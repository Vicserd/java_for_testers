package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "contact firstname")) {
            for (var lastname : List.of("", "contact lastname")) {
                for (var address : List.of("", "contact address")) {
                    for (var mobile : List.of("", "contact mobile")) {
                        for (var email : List.of("", "contact@email")) {
                            result.add(new ContactData()
                                    .withFirstname(firstname)
                                    .withLastname(lastname)
                                    .withAddress(address)
                                    .withMobile(mobile)
                                    .withEmail(email));
                        }
                    }

                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstname(randomString(i * 10))
                    .withLastname(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withMobile(randomString(i * 10))
                    .withEmail(randomString(i * 10)));

        }
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
            .withAddress("").withEmail("").withMobile(""));
    expectedList.sort(compareById);

    // Сравниваем старый и новый список
    Assertions.assertEquals(expectedList, newContacts);
}
}
