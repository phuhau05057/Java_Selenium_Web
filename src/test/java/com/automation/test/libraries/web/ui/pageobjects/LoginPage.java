package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.test.libraries.web.ui.GlobalData.ConfigurationValue;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    String pantryURL = ConfigurationValue.URL;
    String userName = ConfigurationValue.loginUserName;
    String password =ConfigurationValue.loginPassWord;
    private final String txtUserName= "id=email";
    private final String txtPassWord="id=password";
    private final String btnSignIn = "//button[@class='btn btn-primary btn-flat']";

    public WebObject getTxtUsername() {
        return findWebElement(txtUserName);
    }

    public WebObject getTxtPassword() {
        return findWebElement(txtPassWord);
    }

    public WebObject getBtnSignIn(){
        return findWebElement(btnSignIn);
    }

    public void loginPantryForGood() {

        this.navigateToURL(pantryURL);
        getTxtUsername().sendKeys(this.userName,false);
        getTxtPassword().sendKeys(this.password,false);
        getBtnSignIn().click();

        try {
            Thread.sleep(1500);                 //1500 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}