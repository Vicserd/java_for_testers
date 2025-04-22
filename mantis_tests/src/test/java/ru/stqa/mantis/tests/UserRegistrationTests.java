package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

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


}
