import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment {

    AndroidDriver<MobileElement> driver;

    public SearchFragment(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    //---------------Locators---------------
    By gdprAcceptButton = By.id("de.mobile.android.app:id/gdpr_accept_button");
    By filterList = By.id("de.mobile.android.app:id/criteria_container");
    By addCar = By.id("de.mobile.android.app:id/add_new_make_model");
    By listOfVisibleMakes = By.id("de.mobile.android.app:id/entries");
    By okButton = By.id("de.mobile.android.app:id/ok");
    By fromInput = By.id("de.mobile.android.app:id/fromInput");
    By searchButton = By.id("de.mobile.android.app:id/show_results");

    //---------------Methods----------------
    public void clickGdprAccept() {
        driver.findElement(gdprAcceptButton).click();
    }

    public void searchForCar(String make, String model, String fromYear) throws InterruptedException {
        TouchAction tap = new TouchAction(driver);
        System.out.println("Clicking car make,model");
        tap.tap(PointOption.point(530,615)).perform();
        System.out.println("Clicking the Add car button");
        driver.findElement(addCar).click();
        System.out.println("Searching for car make");
        boolean isCarMakeVisible = false;
        while(isCarMakeVisible==false){
            //Thread.sleep(5000);
            List<MobileElement> visibleMakes = new ArrayList<>(driver.findElement(listOfVisibleMakes).findElements(By.xpath("//android.widget.CheckedTextView")));
            for(MobileElement makeElement : visibleMakes){
                if(makeElement.getText().equals(make)){
                    isCarMakeVisible = true;
                    makeElement.click();
                    break;
                }
            }
            if(isCarMakeVisible == false){
                TouchAction touch = new TouchAction(driver);
                touch.press(PointOption.point(500,750)).moveTo(PointOption.point(500,450)).perform();
            }
        }
        System.out.println("Searching for car model");
        boolean isCarModelVisible = false;
        while(isCarModelVisible==false){
            List<MobileElement> visibleModels = new ArrayList<>(driver.findElement(listOfVisibleMakes).findElements(By.xpath("//android.widget.CheckedTextView")));
            for(MobileElement modelElement : visibleModels){
                if(modelElement.getText().equals(model)){
                    isCarModelVisible = true;
                    modelElement.click();
                    break;
                }
            }
            if(isCarModelVisible == false){
                TouchAction touch = new TouchAction(driver);
                touch.press(PointOption.point(500,750)).moveTo(PointOption.point(500,450)).perform();
            }
        }
        System.out.println("Clicking ok");
        driver.findElement(okButton).click();
        System.out.println("Clicking first registration");
        tap.tap(PointOption.point(500,960)).perform();
        System.out.println("Inputting the 'From' year");
        driver.findElement(fromInput).sendKeys(fromYear);
        System.out.println("clicking ok");
        driver.findElement(okButton).click();
        System.out.println("Clicking Search");
        driver.findElement(searchButton).click();
    }
}