package org.web_test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MyBlogTest extends AbstractTest {
    String copyright = "Copyright ⓒ 2022 . Geekbrains";

    @Test
    void postsCountOnPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin(getUsername())
                .inputPassword(getPassword())
                .clickOnElement(loginPage.getLoginButton());

        List<WebElement> posts = new MyBlog(getDriver()).getPosts();
        Assertions.assertTrue(new MyBlog(getDriver()).isNextPageActive());

        if (posts.size() == 0) {
            Assertions.assertFalse(true);
        }
        Assertions.assertTrue(posts.size() == 10);
    }

    @Test
    void nextAndPrevPagesButton() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin(getUsername())
                .inputPassword(getPassword())
                .clickOnElement(loginPage.getLoginButton());

        MyBlog page = new MyBlog(getDriver());
        List<WebElement> posts = page.getPosts();

        if (posts.size() == 4 &&
                page.isPreviousPageDisabled() &&
                page.isNextPageActive()) {
            page.nextPageClick();
            if (page.isPreviousPageActive()) {
                page.previousPageClick();
            } else {
                Assertions.assertFalse(true);
            }
        } else {
            Assertions.assertFalse(true);
        }
    }

    @Test
    void postContents() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin(getUsername())
                .inputPassword(getPassword())
                .clickOnElement(loginPage.getLoginButton());

        List<WebElement> posts = new MyBlog(getDriver()).getPosts();
        if (posts.size() == 0) {
            Assertions.assertFalse(true);
        }

        for (WebElement post : posts) {
            Assertions.assertTrue(post.findElement(By.tagName("h2")).isDisplayed());
            Assertions.assertTrue(post.findElement(By.className("description")).isDisplayed());
            Assertions.assertTrue(post.findElement(By.tagName("img")).isDisplayed());
        }
    }

    @Test
    void copyrightOnPage() {
        String actual = new MyBlog(getDriver())
                .getCopyright().getText();
        Assertions.assertEquals(copyright, actual);
    }
}

