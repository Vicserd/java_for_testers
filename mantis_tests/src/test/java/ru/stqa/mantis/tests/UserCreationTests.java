package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;


import java.time.Duration;
import java.util.stream.Stream;


public class UserCreationTests extends TestBase {

//    public static Stream<String> randomUser() {
//        return Stream.of(CommonFunctions.randomString(8));
//    }


    //9.2
//    @ParameterizedTest
//    @MethodSource ("randomUser")
//    void canCreateUser(String user) {
//        var email = String.format("%s@localhost", user);
//        var password = "password";
//        app.jamesApi().addUser(email,password);
//
//        app.user().startCreation(user, email);
//
//        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
//        var url = CommonFunctions.extractUrl(messages.get(0).content());
//
//        app.user().finishCreation(url, password);
//
//        app.http().login(user, password);
//        Assertions.assertTrue(app.http().isLoggedIn());
//    }

DeveloperMailUser user;
    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.user().startCreation(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(message);

        app.user().finishCreation(url, password);

        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }




}
