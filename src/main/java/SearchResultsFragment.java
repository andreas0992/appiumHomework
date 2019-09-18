import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class SearchResultsFragment {

    AndroidDriver<MobileElement> driver;

    public SearchResultsFragment(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    //---------------Locators---------------
    By carAds = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout" +
            "/android.widget.FrameLayout");

    //---------------Methods----------------
    public ArrayList<MobileElement> getListOfAds() {
        ArrayList<MobileElement> ads = new ArrayList<MobileElement>(driver.findElements(carAds));
        return ads;
    }
}