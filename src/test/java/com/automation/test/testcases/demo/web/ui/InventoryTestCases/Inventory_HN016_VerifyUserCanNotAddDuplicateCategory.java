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

public class Inventory_HN016_VerifyUserCanNotAddDuplicateCategory extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(Inventory_HN016_VerifyUserCanNotAddDuplicateCategory.class);
    final String columnCategory = ConfigurationValue.columnCategory;

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderFactory.class)
    public void TC_TD_Inventory_Category_CanNotAddDuplicate_HN0016(HashMap<String, String> data) throws Exception {

        InventoryPage inventoryPage = new InventoryPage(driver);

        //Login Pantry for Good project
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPantryForGood();

        //Click on Inventory Menu
        inventoryPage.getMenuInventory().click();

        //Add new Item on Category table
        String category = data.get(columnCategory);
        inventoryPage.addNewCategory(category);

        //Add again above item
        inventoryPage.addNewCategory(category);

        //Verify that error message is displayed
        String getMessage = inventoryPage.messageAddDupliate().getText();
        Assert.assertEquals(getMessage,ConfigurationValue.messageDuplicateCategory);

        //Delete Category
        inventoryPage.deleteCategoryItem(category);

    }
}
