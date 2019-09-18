import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Iterator;

public class CarAdFragment {

    AndroidDriver<MobileElement> driver;

    public CarAdFragment(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    //---------------Locators---------------
    By carName = By.id("de.mobile.android.app:id/headline");
    By carDetailsFrame = By.id("de.mobile.android.app:id/attributes");
    By carDetails = By.xpath("//android.widget.TextView");

    //---------------Methods----------------
    public String getCarName() {
        return driver.findElement(carName).getText();
    }
}