import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MobileTests {

    AndroidDriver driver;
    MainFragment mainFragment;
    SearchFragment searchFragment;
    SignInFragment signInFragment;
    RegisterFragment registerFragment;
    SearchResultsFragment searchResultsFragment;
    CarAdFragment carAdFragment;

    @Before
    public void setup() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", "C:\\Users\\asirghie\\Downloads\\mobile de .apk");
        capabilities.setCapability("appWaitActivity", "de.mobile.android.app.splash.Splash");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        mainFragment = new MainFragment(driver);
        searchFragment = new SearchFragment(driver);
        signInFragment = new SignInFragment(driver);
        registerFragment = new RegisterFragment(driver);
        searchResultsFragment = new SearchResultsFragment(driver);
        Thread.sleep(5000);
    }

    @Test
    public void presenceOfMainFragmentElementsTest() throws InterruptedException {
        System.out.println("TESTING THE PRESENCE OF ELEMENTS ON PAGE");
        Assert.assertEquals("Home", mainFragment.checkHomepageText());
        System.out.println("Fragment title Home is displayed correctly");
        Assert.assertEquals("What are you looking for?", mainFragment.getCarSuggestionTitle());
        List<MobileElement> mobileElements = mainFragment.getElements();
        for (MobileElement element : mobileElements) {
            Assert.assertTrue(element.isDisplayed());
            if (element.getTagName().equals("android.widget.Button")) {
                System.out.println("Search button is displayed correctly");
                Assert.assertEquals("SEARCH", mainFragment.getSearchButtonText());
                System.out.println("Search button text is displayed correctly");
            } else {
                System.out.println("Options button is displayed correctly");
            }
        }
        Thread.sleep(3000);

    }

    @Test
    public void searchForCarTest() throws InterruptedException {
        String make = "Dacia";
        String model = "Logan";
        String fromYear = "2010";
        System.out.println("Clicking the search button");
        mainFragment.clickSearchButton();
        System.out.println("Clicking gdpr accept");
        searchFragment.clickGdprAccept();
        searchFragment.searchForCar(make, model, fromYear);
        Thread.sleep(2000);
        ArrayList<MobileElement> ads = searchResultsFragment.getListOfAds();
        for (MobileElement ad : ads) {
            System.out.println("clicking car ad");
            ad.click();
            carAdFragment = new CarAdFragment(driver);
            System.out.println("Checking to see if the make and model are correct");
            Assert.assertTrue(carAdFragment.getCarName().toLowerCase().contains(make.toLowerCase() + " " + model.toLowerCase()));
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            try {
                driver.findElement(By.id("de.mobile.android.app:id/ad_close_button")).click();
            } catch (Exception e) {
            }
        }
        Thread.sleep(5000);

    }

    @Test
    public void loginTest() throws InterruptedException {
        System.out.println("LOG IN TEST");
        String email = "andsrg@mailinator.com";
        String password = "1qaz@WSX";
        System.out.println("Clicking the options button");
        mainFragment.clickOptionsButton();
        //Thread.sleep(1500);
        System.out.println("Clicking the Sign In button");
        mainFragment.clickSignInButton();
        signInFragment.signIn(email, password);
        Thread.sleep(3000);
        TouchAction tap = new TouchAction(driver);
        tap.tap(PointOption.point(500, 70)).perform();
        System.out.println("Clicking the options button");
        mainFragment.clickOptionsButton();
        System.out.println("Checking that the displayed email is "+email);
        Assert.assertEquals(email, mainFragment.getLoggedUserEmail());
    }

    @Test
    public void logInWrongCredentialsTest() throws InterruptedException {
        System.out.println("LOGGING IN WITH WRONG CREDENTIALS");
        String email = "andsrg";
        String password = "1qaz@WSX";
        System.out.println("Clicking the options button");
        mainFragment.clickOptionsButton();
        System.out.println("Clicking the Sign In button");
        mainFragment.clickSignInButton();
        signInFragment.signIn(email, password);
        Thread.sleep(1500);
        TouchAction tap = new TouchAction(driver);
        tap.tap(PointOption.point(500, 950)).perform();
        Assert.assertEquals("User name or password is incorrect",signInFragment.getErrorMessage());
    }

    @Test
    public void registrationTest() throws InterruptedException {
        System.out.println("REGISTRATION TEST");
        System.out.println("Clicking the options button");
        mainFragment.clickOptionsButton();
        System.out.println("Clicking the Sign In button");
        mainFragment.clickSignInButton();
        System.out.println("Clicking the Register button");
        signInFragment.clickRegister();
        //Thread.sleep(3000);
        Date date = new Date();
        String email = "andsrg" + date.getTime() + "@mailinator.com";
        String password = "1qaz@WSX";
        System.out.println("The email used for the test is " + email);
        registerFragment.register(email, password);
        Thread.sleep(3000);
        TouchAction tap = new TouchAction(driver);
        tap.tap(PointOption.point(500, 70)).perform();
        System.out.println("Clicking the options button");
        mainFragment.clickOptionsButton();
        System.out.println("Checking that the displayed email is " + email);
        Assert.assertEquals(email, mainFragment.getLoggedUserEmail());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
