package com.automation.test.testcases.demo.web.ui.FoodScheduleTestCases;

import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.FoodSchedulePageV2;
import com.automation.test.libraries.web.ui.pageobjects.LoginPage;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class FoodSchedule_HN018_VerifySortResultByASConItemColumn extends BaseTest {

    private static final Logger LOGGER = Logger.getLogger(FoodSchedule_HN018_VerifySortResultByASConItemColumn.class);
    final String columnItem = ConfigurationValue.scheduleItemColumn;
    final String sortASCType = ConfigurationValue.sortASC;

    @Test
    public void HN018_VerifySortResultByASConItemColumn() throws Exception {

        FoodSchedulePageV2 foodSchedulePage = new FoodSchedulePageV2(driver);

        //Login Pantry for Good project
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginPantryForGood();

        //Click on Food Schedule Menu
        foodSchedulePage.getMenuFoodSchedule().click();

        //Sort and verify sort result
        foodSchedulePage.verifySortColumnByASC(columnItem);
    }
}
