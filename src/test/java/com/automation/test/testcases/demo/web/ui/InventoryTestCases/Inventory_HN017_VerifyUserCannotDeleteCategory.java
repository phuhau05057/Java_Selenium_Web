package com.automation.test.testcases.demo.web.ui.InventoryTestCases;

import com.automation.framework.core.datadriven.utils.DataProviderFactory;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import com.automation.test.libraries.web.ui.pageobjects.InventoryPage;
import com.automation.test.libraries.web.ui.pageobjects.LoginPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Inventory_HN017_VerifyUserCannotDeleteCategory extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(Inventory_HN017_VerifyUserCannotDeleteCategory.class);
    final String columnCategory = ConfigurationValue.columnCategory;
    final String columnName = ConfigurationValue.columnName;
    final String columnQty = ConfigurationValue.columnQty;
    final String message = ConfigurationValue.messageValidate;

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderFactory.class)
    public void HN017_VerifyUserCannotDeleteCategory(HashMap<String, String> data) throws Exception {

        InventoryPage inventoryPage = new InventoryPage(driver);

        //Login Pantry for Good project
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPantryForGood();

        //Click on Inventory Menu
        inventoryPage.getMenuInventory().click();

        //Add new Item on Category table
        String category = data.get(columnCategory);
        inventoryPage.addNewCategory(category);

        //Add new Inventory and Category is selected on dropdown list
        String name = data.get(columnName);
        String qty = data.get(columnQty);
        inventoryPage.addNewInventory(name,category,qty);

        //Delete Category
        inventoryPage.deleteCategoryItem(category);

        //Verify that error message is displayed
        String messageValidate = data.get(message);
        String getMessage = inventoryPage.getmessageDeleteCateogyUsed().getText();
        Assert.assertEquals(getMessage,messageValidate);

        //Delete Inventory and Category
        inventoryPage.deleteInventoryItemByIndex(name);
        inventoryPage.deleteCategoryItem(category);

    }
}
