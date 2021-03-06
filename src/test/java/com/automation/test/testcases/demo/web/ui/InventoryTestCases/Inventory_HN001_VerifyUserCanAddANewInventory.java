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

public class Inventory_HN001_VerifyUserCanAddANewInventory extends BaseTest
{
    private static final Logger LOGGER = Logger.getLogger(Inventory_HN001_VerifyUserCanAddANewInventory.class);
    final String columnName = ConfigurationValue.columnName;
    final String columnCategory = ConfigurationValue.columnCategory;
    final String columnQty = ConfigurationValue.columnQty;

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderFactory.class)
    public void TC_TD_Inventory_ADD_CreateNew_HN0001(HashMap<String, String> data) throws Exception {

            //Login Pantry for Good project
            LoginPage loginPage = new LoginPage(driver);
            loginPage.loginPantryForGood();

            //Click on Inventory Menu
            InventoryPage inventoryPage = new InventoryPage(driver);
            inventoryPage.getMenuInventory().click();

            // Create new Food
            String name = data.get(columnName);
            String category = data.get(columnCategory);
            String qty = data.get(columnQty);

            //Create new Inventory items then checking created item
            inventoryPage.addNewInventory(name,category,qty);

            //Verify New Inventory is added correctly
            inventoryPage.getTxtSearchInventory().sendKeys(name,false);
            inventoryPage.checkInventoryItemByIndex(name,category,qty);

            //Delete created item after creating
            inventoryPage.deleteInventoryItemByIndex(name);

    }
}
