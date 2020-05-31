package com.automation.test.testcases.demo.web.ui.InventoryTestCases;

import com.automation.framework.core.datadriven.utils.DataProviderFactory;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.LoginPage;
import com.automation.test.libraries.web.ui.pageobjects.InventoryPage;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

public class Inventory_HN006_VerifyMessageIsDisplayedWhenUserSearchesForNonExistingInventory extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(Inventory_HN006_VerifyMessageIsDisplayedWhenUserSearchesForNonExistingInventory.class);
    final String columnName = ConfigurationValue.columnName;
    final String columnCategory = ConfigurationValue.columnCategory;
    final String columnQty = ConfigurationValue.columnQty;

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderFactory.class)
    public void TC_TD_Inventory_Search_NonExistingwithMessage_HN0006(HashMap<String, String> data) throws Exception {

            //Login Pantry for Good project
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginPantryForGood();

            //***This testcase will be covered by below steps
            // Create new Inventory -> Delete Inventory -> Searching this Inventory -> Message will be show correctly
            InventoryPage inventoryPage = new InventoryPage(driver);
            //Click on Inventory Menu
            inventoryPage.getMenuInventory().click();
            // Create new Inventory
            String name = data.get(columnName);
            String category = data.get(columnCategory);
            String qty = data.get(columnQty);
            inventoryPage.addNewInventory(name,category,qty);

            // Delete created Inventory
            inventoryPage.deleteInventoryItemByIndex(name);

            // Verify that message is displayed when searching non-existing Inventory item
            String actualTextOnSearchResult = inventoryPage.getLblSearchNoResult();
            String expectMessage = "No foods in inventory matching " + name;
            Assert.assertEquals(actualTextOnSearchResult,expectMessage);
        }
}
