package stepsDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utilities.Config;
import utilities.Driver;

public class LoginDemoSteps {
    LoginPage loginPage = new LoginPage();


    @Given("user is on sauce demo login page")
    public void user_is_on_sauce_demo_login_page() {
        Driver.getDriver().get(Config.getProperty("sauceDemo"));
    }

    @When("user provides a valid username")
    public void user_provides_a_valid_username() {
        loginPage.userNameInputField.sendKeys(Config.getProperty("sauceUser"));
    }

    @When("user provides a valid password")
    public void user_provides_a_valid_password() {
        loginPage.passwordInputField.sendKeys(Config.getProperty("saucePassword"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage.loginButton.click();
    }

    @Then("verify user successfully logged in")
    public void verify_user_successfully_logged_in() {
        Assert.assertEquals(Config.getProperty("productPageURL"), Driver.getDriver().getCurrentUrl());
        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().close();
        Driver.getDriver().quit();


    }

    @When("user provides a invalid username")
    public void user_provides_a_invalid_username() {
        loginPage.userNameInputField.sendKeys(Config.getProperty("sauceInvalidUser"));
    }

    @When("user provides a invalid password")
    public void user_provides_a_invalid_password() {
        loginPage.passwordInputField.sendKeys(Config.getProperty("sauceInvalidPassword"));
    }

    @Then("verify user sees an error message")
    public void verify_user_sees_an_error_message() {
        Assert.assertEquals(Config.getProperty("sauceInvalidErrorMessage"), loginPage.InvalidLoginErrorMessage.getText());
    }
}
