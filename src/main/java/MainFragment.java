import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class MainFragment {

    AndroidDriver<MobileElement> driver;

    public MainFragment(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    //---------------Locators---------------
    By searchButton = By.id("de.mobile.android.app:id/btn_search");
    By toolBar = By.id("de.mobile.android.app:id/toolbar");
    By optionButton = By.id("Open navigation bar");
    By carSuggestions = By.id("de.mobile.android.app:id/ll_item_discovery_header");
    By signInButton = By.id("de.mobile.android.app:id/navigation_drawer_footer_button_login");
    By loggedUserEmail = By.id("de.mobile.android.app:id/drawer_header_email");

    //---------------Methods----------------
    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void clickOptionsButton(){
        driver.findElement(optionButton).click();
    }

    public String checkHomepageText(){

        String homeText = driver.findElement(toolBar).findElement(By.xpath("//android.widget.TextView")).getText();
        return homeText;
    }

    public String getCarSuggestionTitle(){
        String suggestionsTitle = driver.findElement(carSuggestions).findElement(By.xpath("//android.widget.TextView")).getText();
        return suggestionsTitle;
    }

    public String getSearchButtonText(){
        return driver.findElement(searchButton).getText();
    }

    public List getElements(){
        List<MobileElement> listOfElements = new ArrayList(List.of(driver.findElement(searchButton),driver.findElement(optionButton)));
        return listOfElements;
    }

    public void clickSignInButton(){
        driver.findElement(signInButton).click();
    }

    public String getLoggedUserEmail(){
        return driver.findElement(loggedUserEmail).getText();
    }
}