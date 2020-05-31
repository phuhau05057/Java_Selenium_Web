package com.automation.test.testcases.demo.web.ui.InventoryTestCases;

import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.LoginPage;
import com.automation.test.libraries.web.ui.pageobjects.InventoryPage;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class Inventory_HN010_VerifyInventoriesAresortedbyCategoryWithDESCType extends BaseTest{

    private static final Logger LOGGER = Logger.getLogger(Inventory_HN010_VerifyInventoriesAresortedbyCategoryWithDESCType.class);
    final String columnCategory = ConfigurationValue.columnCategory;
    final String sortDESC = ConfigurationValue.sortDESC;

    @Test
    public void HN010_VerifyInventoriesAresortedbyCategoryWithDESCType() throws Exception {

        InventoryPage inventoryPage = new InventoryPage(driver);

        //Login Pantry for Good project
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPantryForGood();

        //Click on Inventory Menu
        inventoryPage.getMenuInventory().click();

        //Sort and verify sort result
        inventoryPage.verifySortColumnByDESC(columnCategory);
    }
}
