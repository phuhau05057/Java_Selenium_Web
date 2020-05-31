package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodSchedulePageV2 extends BasePage {

    public FoodSchedulePageV2(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(),this);

    }

//    @FindBy(xpath = "//a/span[text()='Food Schedule']")
//    private WebElement menuFoodSchedulev2;

    private final String menuFoodSchedule = "//a/span[text()='Food Schedule']";

    public WebObject getMenuFoodSchedule()
    {
        return findWebElement(menuFoodSchedule);
    }

    public List<String> returnListOfItemByColumns(String column) {

        String indexColumn = "";

        switch (column) {
            case "Item":
                indexColumn = "1";
                break;

            case "Category":
                indexColumn = "2";
                break;

            case "Start Date":
                indexColumn = "3";

            case "Frequency":
                indexColumn = "4";
        }

        String elementOfColumn = "//div[@class='react-bs-container-body']/table/tbody//td[" + indexColumn + "]";
        System.out.println("element after using switch case" + elementOfColumn);

        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = getWebDriver().findElements(By.xpath(elementOfColumn));
        System.out.println("elementlist" + elementList);
        for (WebElement we : elementList) {
            obtainedList.add(we.getText());
        }

        System.out.println("List get form table" + obtainedList);

        return obtainedList;

    }

    public void verifySortColumnByASC(String nameofColumn) {

        //Identify the element of Header from variable
        String elementofHeader = "//th[@title='" + nameofColumn + "']/span";
        WebElement getHeader = getWebDriver().findElement(By.xpath(elementofHeader));

        // For exactly status of Sort button. We need to click on first time for getting Status
        getHeader.click();

        //Get current status of Sort button and make click/unclick based on SortType
        String getCurrentSortStatuofName = getHeader.getAttribute("class");
        if (!getCurrentSortStatuofName.equals("order dropup")) {
            getHeader.click();
        }

        // Retrieve List of item by Column
        ArrayList<String> obtainedList = new ArrayList<>(returnListOfItemByColumns(nameofColumn));
        System.out.println("obtainedList " + obtainedList);

        //Put the list of Item from obtainedList into sortedList then using Collection.sort from Java
        ArrayList<String> sortedList = new ArrayList<>();

        for (String s :obtainedList)
        {
            sortedList.add(s);
        }
        Collections.sort(sortedList);

        //Compare sort between sortedList and obtainedList
        Assert.assertTrue(sortedList.equals(obtainedList));

    }

    public void verifySortColumnByDESC(String nameofColumn) {

        //Identify the element of Header from variable
        String elementofHeader = "//th[@title='" + nameofColumn + "']/span";
        WebElement getHeader = getWebDriver().findElement(By.xpath(elementofHeader));

        // For exactly status of Sort button. We need to click on first time for getting Status
        getHeader.click();

        //Get current status of Sort button and make click/unclick based on SortType
        String getCurrentSortStatuofName = getHeader.getAttribute("class");
        if (!getCurrentSortStatuofName.equals("order")) {
            getHeader.click();
        }

        // Retrieve List of item by Column
        ArrayList<String> obtainedList = new ArrayList<>(returnListOfItemByColumns(nameofColumn));
        System.out.println("obtainedList " + obtainedList);

        //Put the list of Item from obtainedList into sortedList then using Collection.sort from Java
        ArrayList<String> sortedList = new ArrayList<>();

        for (String s :obtainedList)
        {
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);

        //Compare sort between sortedList and obtainedList
        Assert.assertTrue(sortedList.equals(obtainedList));

    }
}
