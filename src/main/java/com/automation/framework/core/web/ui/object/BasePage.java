package com.automation.framework.core.web.ui.object;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

enum SearchBy {
    ID,
    XPath,
    Name
}

public class BasePage {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class);

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public WebObject findWebElement(String locator) {
        SearchBy searchType = SearchBy.ID;
        String searchStr = locator;
        WebObject findObject = null;

        if (locator.startsWith("//")) {
            searchType = SearchBy.XPath;
        } else {
            int idx = locator.indexOf('=');

            if (idx > 0) {
                String locatorHead = locator.substring(0, idx);
                SearchBy lookingUpSearchBy = null;

                for (SearchBy s : SearchBy.values()) {
                    if (s.name().equalsIgnoreCase(locatorHead)) {
                        lookingUpSearchBy = s;
                        break;
                    }
                }

                if (lookingUpSearchBy == null) {
                    throw new NullPointerException("SearchBy header text not found");
                }

                searchType = lookingUpSearchBy;
                searchStr = locator.substring(idx + 1);
            }
        }

        try {
            switch (searchType) {
                case ID:
                    findObject = new WebObject(driver.findElement(new By.ById(searchStr)));
                    break;
                case XPath:
                    findObject = new WebObject(driver.findElement(new By.ByXPath(searchStr)));
                    break;
                case Name:
                    findObject = new WebObject(driver.findElement(new By.ByName(searchStr)));
                    break;
                default:
                    break;
            }
        } catch (StaleElementReferenceException ex) {
            LOGGER.error(ex.getMessage());
        }

        return findObject;
    }

    public void navigateToURL(String sURL) {
        driver.navigate().to(sURL);
    }

    public boolean isPageTitleContains(String searchStr) {
        return driver.getTitle().contains(searchStr);
    }
}
