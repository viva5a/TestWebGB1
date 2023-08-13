package org.web_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginPageTest extends AbstractTestClass {
    String copyright = "Copyright ⓒ 2022 . Geekbrains";
    String expected401 = "401\nInvalid credentials.";
    String expected500 = "500: Argument is not a ByteString";

    @Test
    void copyrightOnPage() {
        String actual = new LoginPage(getDriver())
                .getCopyrightText();
        Assertions.assertEquals(copyright, actual);
    }

    @Test
    void loginWithValidUserData() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin(getUsername())
                .inputPassword(getPassword())
                .clickOnElement(loginPage.getLoginButton());
        Assertions.assertEquals("Blog", loginPage.getBlogText());
    }

    @Test
    void loginWithNotValidUserData() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin(getUsername() + "1")
                .inputPassword(getPassword() + "1")
                .clickOnElement(loginPage.getLoginButton());
        Assertions.assertEquals(expected401, loginPage.getErrorBlock401().getText());
    }

    @Test
    void loginWithEmptyLoginField() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputPassword(getPassword())
                .clickOnElement(loginPage.getLoginButton())
                .getError401();
        Assertions.assertEquals(expected401, loginPage.getErrorBlock401().getText());
    }

    @Test
    void loginWithEmptyPasswordField() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin(getUsername())
                .clickOnElement(loginPage.getLoginButton())
                .getError401();
        Assertions.assertEquals(expected401, loginPage.getErrorBlock401().getText());
    }

    @Test
    void loginWithNoLatinLoginData() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin("привет228")
                .inputPassword("5b9398de16")
                .clickOnElement(loginPage.getLoginButton())
                .getError500();
        Assertions.assertEquals(expected500, loginPage.getErrorBlock500().getText());
    }

    @Test
    void loginWithNoValidLoginDataLessSymbol() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin("l2")
                .inputPassword("bec2567577")
                .clickOnElement(loginPage.getLoginButton())
                .getError500();
        Assertions.assertEquals(expected500, loginPage.getErrorBlock500().getText());
    }

    @Test
    void loginWithNoValidLoginDataMoreSymbol() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin("qwertyuiopasdfghjkl21")
                .inputPassword("4b30ab061a")
                .clickOnElement(loginPage.getLoginButton())
                .getError500();
        Assertions.assertEquals(expected500, loginPage.getErrorBlock500().getText());
    }
}