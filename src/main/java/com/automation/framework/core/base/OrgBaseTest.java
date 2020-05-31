package com.automation.framework.core.base;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(OrgBaseTest.class);

    @BeforeMethod(
            alwaysRun = true
    )
    public void beforeMethod(Method method, Object[] testData) {
        Test testMethod = (Test)method.getAnnotation(Test.class);
        LOGGER.info("Start Test: " + (testMethod.testName().equals("") ? method.getName() : testMethod.testName()));
    }
}
