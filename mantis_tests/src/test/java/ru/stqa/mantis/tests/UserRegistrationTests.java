package ru.stqa.mantis.tests;

import io.swagger.client.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static io.swagger.client.auth.OAuthFlow.password;

public class UserRegistrationTests extends TestBase {

    public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }


    @Test
    void canRegisterUser() {
        var username = "user1";
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesCli().addUser(email, password);  // создать пользователя (адресс) на почтовом сервере (JamesHelper)
        app.user().signUpUser(username, email);  // открыть браузер и заполнить форму создания и отправить (браузер)
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));//(Ждем) получить почту (MailHelper)
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);

        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);        //извлечь ссылку из письма
            app.user().editAccount(url, username, "password");            //вернуться в браузер, пройти по ссылке и завершить регистпрацию пользователя (браузер)

        }
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }



    @Test
    void canRegisterNewUserRest() {
        //создать новый адрес на почтовом сервере James
        UserData newUser = new UserData().withName(CommonFunctions.randomString(7))
                .withEmail(String.format("%s@localhost", CommonFunctions.randomString(6)))
                .withPassword("password");
        app.jamesApi().addUser(newUser.email(), newUser.password());

        //создать нового пользователя в Mantis
        app.rest().createUser(newUser);

        // получить почту (MailHelper)
        var messages = app.mail().receive(newUser.email(), newUser.password(), Duration.ofSeconds(60));
        //извлечь ссылку из письма
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        //пройти по ссылке и завершить регистрацию
        app.user().editAccount(url, newUser.name(), newUser.password());


        //проверить вход под новым пользователем
        app.http().login(newUser.name(), newUser.password());
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
