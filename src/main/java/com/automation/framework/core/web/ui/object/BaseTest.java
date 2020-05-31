package com.automation.framework.core.web.ui.object;

import com.automation.framework.core.base.OrgBaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import java.io.File;

public class BaseTest extends OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeTest
    public void runBeforeTest() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);

    }

    @AfterTest
    public void runAfterTest() {
        closeBrowser();
    }

    public void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
