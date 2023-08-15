package org.web_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AbstractTest {
    static ChromeDriver driver;
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String username;
    private static String password;
    private static String base_url;
    private static String token;
    private static Long time = 5l;

    @BeforeEach
    void init()
            throws IOException
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        driver.get("https://test-stand.gb.ru/login");

        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        username = prop.getProperty("username");
        password = prop.getProperty("password");
        base_url = prop.getProperty("base_url");
        token = prop.getProperty("token");
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getBase_url() {
        return base_url;
    }

    public static String getToken() {
        return token;
    }

    public static ChromeDriver getDriver() {
        return driver;
    }

    @AfterEach
    void close() {
        driver.close();
    }
}

