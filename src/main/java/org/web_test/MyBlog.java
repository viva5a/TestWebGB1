package org.web_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MyBlog
{
    private final WebDriver driver;


    @FindBy(xpath = "//div[@class='content']/div[contains(@class,'posts')]/a")
    private List<WebElement> posts;
    @FindBy(xpath = "//a[.='Next Page' and contains(@href,'page')]")
    private WebElement nextPageActive;
    @FindBy(xpath = "//a[.='Next Page' and contains(@class,'disabled')]")
    private WebElement nextPageDisabled;
    @FindBy(xpath = "//a[.='Previous Page' and contains(@href,'page')]")
    private WebElement previousPageActive;
    @FindBy(xpath = "//a[.='Previous Page' and contains(@class,'disabled')]")
    private WebElement previousPageDisabled;
    @FindBy(xpath = "//div[.='Copyright ⓒ 2022 . Geekbrains']")
    private WebElement copyright;


    public MyBlog(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isNextPageDisabled() {
        return nextPageDisabled.isDisplayed();
    }

    public boolean isPreviousPageDisabled() {
        return previousPageDisabled.isDisplayed();
    }

    public boolean isNextPageActive() {
        return nextPageActive.isDisplayed();
    }

    public boolean isPreviousPageActive() {
        return previousPageActive.isDisplayed();
    }

    public MyBlog nextPageClick() {
        nextPageActive.click();
        return this;
    }

    public MyBlog previousPageClick() {
        previousPageActive.click();
        return this;
    }

    public List<WebElement> getPosts() {
        return posts;
    }

    public WebElement getCopyright() {
        return copyright;
    }
}