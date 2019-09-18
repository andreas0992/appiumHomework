import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterFragment {

    AndroidDriver<MobileElement> driver;

    public RegisterFragment(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    //---------------Locators---------------
    By emailField = By.id("de.mobile.android.app:id/my_mobile_register_email");
    By passwordField = By.id("de.mobile.android.app:id/my_mobile_register_password");
    By termsAndConditionsCheckbox = By.id("de.mobile.android.app:id/my_mobile_privacy_checkbox");
    By registerButton = By.id("de.mobile.android.app:id/register");

    //---------------Methods----------------
    public void register(String email, String password) {
        System.out.println("Inputting email");
        driver.findElement(emailField).sendKeys(email);
        System.out.println("Inputting password");
        driver.findElement(passwordField).sendKeys(password);
        System.out.println("Clicking the terms and conditions checkbox");
        driver.findElement(termsAndConditionsCheckbox).click();
        driver.hideKeyboard();
        System.out.println("Clicking Register");
        driver.findElement(registerButton).click();
    }
}