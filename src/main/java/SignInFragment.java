import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInFragment {

    AndroidDriver<MobileElement> driver;

    public SignInFragment(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    //---------------Locators---------------
    By emailField = By.id("de.mobile.android.app:id/my_mobile_email");
    By passwordField = By.id("de.mobile.android.app:id/my_mobile_password");
    By signInButton = By.id("de.mobile.android.app:id/login");
    By headerButtons = By.id("de.mobile.android.app:id/page_title");
    By errorMessage = By.id("de.mobile.android.app:id/content_text");

    //---------------Methods----------------
    public void signIn(String email, String password) {
        System.out.println("Inputting the email");
        driver.findElement(emailField).sendKeys(email);
        System.out.println("Inputting the password");
        driver.findElement(passwordField).sendKeys(password);
        System.out.println("Clicking the Sign in button");
        driver.findElement(signInButton).click();
    }

    public void clickRegister(){
        driver.findElements(headerButtons).get(1).click();
    }

    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }
}