package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase{
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }
    public void signUpUser(String username, String email) {
        openLoginPage();
        openSignUpPage();
        fillRegistrationForm();
        proceedRegistration();
    }

    private void proceedRegistration() {

        click(By.linkText("Proceed"));
    }

    private void fillRegistrationForm() {
        type(By.id("username"), "user");
        type(By.name("email"), "user1@localhost");
        click(By.cssSelector("input[type='submit']"));
    }

    private void openSignUpPage() {
        click(By.linkText("Signup for a new account"));
    }

    private void openLoginPage() {
            manager.driver().get(String.format("%s/login_page.php", manager.property("web.baseUrl")));
        }

    public void editAccount(String url, String username, String password) {
        clickConfirmLink(url);
        fillEditAccountForm(username, password);
        submitUpdate(); 
    }

    private void submitUpdate() {
        click(By.cssSelector("button[type='submit']"));
    }

    private void fillEditAccountForm(String username, String password) {
        click(By.id("realname"));
        type(By.name("realname"), username);
        click(By.id("password"));
        type(By.name("password"), password);
        click(By.id("password-confirm"));
        type(By.name("password_confirm"), password);
    }

    private void clickConfirmLink(String url) {
        manager.driver().get(url);
    }

    public void startCreation(String user, String email) {
        if (!manager.session().isLoggedIn()) {
            manager.session().login(manager.property("web.username"), manager.property("web.password"));
        }
        manager.driver().get(String.format("%s/manage_user_create_page.php", manager.property("web.baseUrl")));
        type(By.name("username"), user);
        type(By.name("realname"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void finishCreation(String confirmationUrl, String password) {
        manager.driver().get(confirmationUrl);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

}


