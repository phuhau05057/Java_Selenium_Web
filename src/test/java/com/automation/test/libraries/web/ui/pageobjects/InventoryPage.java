package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


import org.testng.Assert;


public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    //Its used for Foods table
    private final String menuInventory = "//a/span[text()='Inventory']";
    private final String tableRecordofInventory = "class=table table-striped table-bordered";
    private final String btnAddToInventory = "//button[@class='btn-success btn btn-default']";
    private final String txtFoodName = "id=foodName";
    private final String selectCategory = "foodCategory";
    private final String txtQuantity = "id=foodQuantity";
    private final String btnAddFood = "//div[@class='pull-right btn-toolbar']/button[2]";
    private final String textSearchInventory = "//div/input[@placeholder='Search']";
    private final String tableInventory = "//div[@class='react-bs-table-container']//table[@class='table table-striped table-bordered']//tbody";
    private final String lblSearchNoResult = "//div[@class='react-bs-table-container']//table[@class='table table-striped table-bordered']//tbody/tr/td[contains(text(),'No foods in inventory matching')]";
    private final String btnDeleteInventory = "//button[@class='btn btn-sm btn-danger']";
    private final String lblSizeofPage = "id=pageDropDown";
    private final String headerName = "//th[@title='Name']/span";
    private final String headerCategory = "//th[@title='Category']/span";
    private final String headerQty = "//th[@title='Qty']/span";

    //Its used for Categories table
    private final String txtAddCategory = "//input[@placeholder='Add category']";
    private final String btnAddCategory = "//span[@class='input-group-btn']/button";
    private final String tableCategory = "//div[@class='box box-primary']//div[@class='box-body']/div/div/table/tbody";
    private final String txtEditCatgory = "//input[@type='text' and @required]";
    private final String btnCofirmEditCategory = "//button/i[@class='fa fa-check']";
    private final String messageDuplicateItem = "//div/strong";
    private final String messageDeleteCateogyUsed = "//div[@class='text-danger']";


    public WebObject getMenuInventory() {
        return findWebElement(menuInventory);
    }

    public WebObject getBtnAddToInventory() {
        return findWebElement(btnAddToInventory);
    }

    public WebObject gettxtFoodName() {
        return findWebElement(txtFoodName);
    }

    public WebObject gettxtQuantity() {
        return findWebElement(txtQuantity);
    }

    public WebObject getbtnAddFood() {
        return findWebElement(btnAddFood);
    }

    public WebObject getTxtSearchInventory() {
        return findWebElement(textSearchInventory);
    }

    public WebObject getBtnDeleteInventory() {
        return findWebElement(btnDeleteInventory);
    }

    public WebObject getlblSzieofPage() {
        return findWebElement(lblSizeofPage);
    }

    public WebObject getHeaderName() {
        return findWebElement(headerName);
    }

    public WebObject getHeaderCategory() {
        return findWebElement(headerCategory);
    }

    public WebObject getHeaderQty() {
        return findWebElement(headerQty);
    }

    public WebObject gettxtAddCategory() {
        return findWebElement(txtAddCategory);
    }

    public WebObject getbtnAddCategory() {
        return findWebElement(btnAddCategory);
    }

    public WebObject gettxtEditCategory()
    {
        return findWebElement(txtEditCatgory);
    }

    public WebObject messageAddDupliate()
    {
        return findWebElement(messageDuplicateItem);
    }

    public WebObject getbtnConfirmEditCategory()
    {
        return findWebElement(btnCofirmEditCategory);
    }

    public WebObject getmessageDeleteCateogyUsed()
    {
        return findWebElement(messageDeleteCateogyUsed);
    }

    public void selectDropdownByText(String element, String nameOfSelect) {
        //Identify dropdown by ID of tag
        Select dropdown = new Select(getWebDriver().findElement(By.id(element)));

        dropdown.selectByVisibleText(nameOfSelect);
    }

    public void addNewInventory(String foodName, String category, String quantity) throws Exception {

        //Click Add to Inventory button on Inventory page
        getBtnAddToInventory().click();

        //Enter and select value into all fields on Add Food popup then Clicking Add button
        gettxtFoodName().sendKeys(foodName, false);
        gettxtQuantity().sendKeys(quantity, false);
        this.selectDropdownByText(selectCategory, category);
        getbtnAddFood().click();

        // Search new Inventory on Search TextBox

        getTxtSearchInventory().sendKeys(foodName, false);
//        //Verify that new Food is added
//        checkInventoryItemByIndex(foodName,category,quantity);

    }

    public int getIndexTableInventory(String tdname) {

        int result = -9999;

        WebElement tableBody = getWebDriver().findElement(By.xpath(tableInventory));

        List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));

        for (int i = 0; i < tableRows.size(); i++) {
            WebElement row = tableRows.get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(tdname)) {
                result = i + 1;

                break;
            }
        }
        return result;
    }

    public void checkInventoryItemByIndex(String nameInventory, String categoryInventory, String qtyInventory) throws Exception {

        int value = getIndexTableInventory(nameInventory);

        String row = Integer.toString(value);

        String cellName = "/td[1]";
        String cellCategory = "/td[2]/div";
        String cellQty = "/td[3]";

        String elementofName = tableInventory + "/tr[" + row + "]" + cellName;
        String elementofCateogry = tableInventory + "/tr[" + row + "]" + cellCategory;
        String elementofQty = tableInventory + "/tr[" + row + "]" + cellQty;

        //Get Text from elements
        String valueofName = findWebElement(elementofName).getText();
        String valueofCategory = findWebElement(elementofCateogry).getText();
        String valueofQty = findWebElement(elementofQty).getText();

        //Compare value between datamaping and observed data
        Assert.assertEquals(nameInventory, valueofName);
        Assert.assertEquals(categoryInventory, valueofCategory);
        Assert.assertEquals(valueofQty,qtyInventory);
    }

    public void deleteInventoryItemByIndex(String nameInventory) throws Exception {

        //Start with searching Inventory which is needed to delete

        getTxtSearchInventory().sendKeys(nameInventory, false);

        //Click Delete button for deleted item
        int value = getIndexTableInventory(nameInventory);

        String row = Integer.toString(value);

        String cellDeleteButton = "/td[4]//button[@class='btn btn-xs btn-danger']";

        String elementofDeleteButton = tableInventory + "/tr[" + row + "]" + cellDeleteButton;

        getWebDriver().findElement(By.xpath(elementofDeleteButton)).click();
        getBtnDeleteInventory().click();

    }

    public void updateInventoryItemByIndex(String nameInventory, String categoryInventory, String qtyInventory) throws Exception {

        //Start with searching Inventory which is needed to update

        getTxtSearchInventory().sendKeys(nameInventory, false);

        //Click Edit button button for deleted item
        int value = getIndexTableInventory(nameInventory);

        String row = Integer.toString(value);

        String cellUpdateButton = "/td[4]//button[@class='btn btn-xs btn-primary']";

        String elementofUpdateButton = tableInventory + "/tr[" + row + "]" + cellUpdateButton;

        getWebDriver().findElement(By.xpath(elementofUpdateButton)).click();

        //Enter and select value into all fields on Add Food popup then Clicking Add button
        gettxtQuantity().sendKeys(qtyInventory, false);
        this.selectDropdownByText(selectCategory, categoryInventory);
        getbtnAddFood().click();

    }

    public String checkWorkingSizeofPage(int size) {

        String result = "";

        //Select size of table which following variable on this method
        //click on dropdown size
        getlblSzieofPage().click();
        //Identify element of selected size then clicking selected size
        WebElement lblSelectedSize = getWebDriver().findElement(By.xpath("//a[@role='menuitem' and @data-page='" + Integer.toString(size) + "']"));
        lblSelectedSize.click();

        WebElement tableBody = getWebDriver().findElement(By.xpath(tableInventory));

        List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));

        if (tableRows.size() <= size) {
            return result = "Table displays only equal or less than " + Integer.toString(size);
        } else {
            return result = "Table displayed number of record is not matching with size of table" + Integer.toString(size);
        }
    }

    public List<String> returnListOfItemByColumns(String column) {

        String indexColumn = "";

        switch (column) {
            case "Name":
                indexColumn = "1";
                break;

            case "Category":
                indexColumn = "2";
                break;

            case "Qty":
                indexColumn = "3";
        }

        String elementOfColumn = "//div[@class='react-bs-container-body']/table/tbody//td[" + indexColumn + "]";

        ArrayList<String> obtainedList = new ArrayList<>();
        List<WebElement> elementList = getWebDriver().findElements(By.xpath(elementOfColumn));
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

    public String getLblSearchNoResult() {
        WebElement getResult = getWebDriver().findElement(By.xpath(lblSearchNoResult));

        String returnActualText = getResult.getText();

        return returnActualText;
    }

    public void addNewCategory(String nameCategory) {
        gettxtAddCategory().sendKeys(nameCategory, false);
        getbtnAddCategory().click();
    }

    public int getCategorybyIndex(String nameCateogry) {
        int result = -9999;
        WebElement tableCategoryBody = getWebDriver().findElement(By.xpath(tableCategory));
        List<WebElement> tableRows = tableCategoryBody.findElements(By.tagName("tr"));

        for (int i = 0; i < tableRows.size(); i++) {
            WebElement row = tableRows.get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(nameCateogry)) {
                result = i + 1;

                break;
            }
        }
        return result;
    }

    public void deleteCategoryItem(String nameofCategory)
    {
        //Click Delete button for deleted item
        int value = getCategorybyIndex(nameofCategory);

        String row = Integer.toString(value);

        String cellDeleteButton = "//i[@class='fa fa-trash-o text-red']";

        String elementofDeleteButton= tableCategory + "/tr[" + row + "]" + cellDeleteButton;

        getWebDriver().findElement(By.xpath(elementofDeleteButton)).click();

    }

    public void updateCategoryItem(String nameofOldCategory,String nameofNewCategory)
    {
        //Click Delete button for deleted item
        int value = getCategorybyIndex(nameofOldCategory);

        String row = Integer.toString(value);

        String cellUpdateButton = "//i[@class='fa fa-edit text-blue']";

        String elementofUpdateButton= tableCategory + "/tr[" + row + "]" + cellUpdateButton;

        getWebDriver().findElement(By.xpath(elementofUpdateButton)).click();
        gettxtEditCategory().sendKeys(nameofNewCategory,false);
        getbtnConfirmEditCategory().click();

    }

    public void checkCategoryIsExisting(String nameofCategory,boolean existing)
    {
        String actualResult = "";
        String existingMessage = "This Category is existing on page";
        String nonExisentMessage = "This Cateogry is not existing on page";

        WebElement tableCategoryBody = getWebDriver().findElement(By.xpath(tableCategory));

        List<WebElement> tableRows = tableCategoryBody.findElements(By.tagName("tr"));

        for (int i = 0; i < tableRows.size(); i++) {
            WebElement row = tableRows.get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals(nameofCategory)) {

                actualResult = "This Category is existing on page";

                break;
            }
            else actualResult = "This Cateogry is not existing on page" ;
        }

        if (existing == true)
        {
            Assert.assertEquals(actualResult,existingMessage);
        }
        else Assert.assertEquals(actualResult,nonExisentMessage);
    }
}
