package com.automation.framework.core.web.ui.object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebObject {
    private WebElement webElement;

    public WebObject(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getText() {
        return webElement.getText();
    }

    public String getAttribute(String attribute){
        return webElement.getAttribute(attribute);
    }
    public String getValue() {
        return webElement.getAttribute("value");
    }

    public void click() {
        webElement.click();
    }

    public void sendKeys(String inputText, boolean submit) {
        webElement.clear();

        webElement.sendKeys(inputText);

        if (submit) {
            webElement.submit();
        }
    }

    public  void selectDropdownByText ( String id,String nameOfSelect){
        //Identify dropdown by ID of tag
        Select dropdown = new Select(webElement.findElement(By.id(id)));

        dropdown.selectByVisibleText(nameOfSelect);
    }

}
