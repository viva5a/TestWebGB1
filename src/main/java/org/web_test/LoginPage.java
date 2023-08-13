package org.web_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//span[.='Login']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[.='Copyright ⓒ 2022 . Geekbrains']")
    private WebElement copyright;
    @FindBy(xpath = "//h1[.='Blog']")
    private WebElement blogHeader;
    @FindBy(xpath = "//div[contains(@class,'error-block')]")
    private WebElement errorBlock401;
    @FindBy(xpath = "//p[contains(@class,'svelte-195leua')]")
    private WebElement errorBlock500;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getCopyright() {
        return copyright;
    }

    public WebElement getBlogHeader() {
        return blogHeader;
    }

    public WebElement getErrorBlock401() {
        return errorBlock401;
    }

    public WebElement getErrorBlock500() {
        return errorBlock500;
    }

    public LoginPage clickOnElement(WebElement webElement) {
        webElement.click();
        return this;
    }

    public LoginPage moveToElement(WebElement webElement) {
        new Actions(driver).moveToElement(webElement).perform();
        return this;
    }

    public LoginPage inputLogin(String login) {
        usernameField.clear();
        usernameField.sendKeys(login);
        return this;
    }

    public LoginPage inputPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public String getBlogText() {
        return blogHeader.getText();
    }

    public String getCopyrightText() {
        return copyright.getText();
    }

    public String getError401() {
        return errorBlock401.getText();
    }

    public String getError500() {
        return errorBlock500.getText();
    }
}