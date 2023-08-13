package org.web_test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class PageMyPostsTest extends AbstractTestClass {
    String copyright = "Copyright ⓒ 2022 . Geekbrains";

    @Test
    void postsCountOnPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage
                .inputLogin(getUsername())
                .inputPassword(getPassword())
                .clickOnElement(loginPage.getLoginButton());

        List<WebElement> posts = new MyPostsPage(getDriver()).getPosts();
        Assertions.assertTrue(new MyPostsPage(getDriver()).isNextPageActive());

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

        MyPostsPage page = new MyPostsPage(getDriver());
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

        List<WebElement> posts = new MyPostsPage(getDriver()).getPosts();
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
        String actual = new MyPostsPage(getDriver())
                .getCopyright().getText();
        Assertions.assertEquals(copyright, actual);
    }
}
