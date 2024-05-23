package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "user-name")
    public WebElement userNameInputField;

    @FindBy(id = "password")
    public WebElement passwordInputField;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    public WebElement InvalidLoginErrorMessage;

}
