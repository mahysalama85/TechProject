package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utilities.DriverFactory;

import static org.junit.Assert.*;

public class OrangeHRMSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    AdminPage adminPage = new AdminPage(driver);
    AddUserPage addUserPage = new AddUserPage(driver);

    int oldCount;
    int newCount;
    String createdUsername;

    @Given("I navigate to OrangeHRM login page")
    public void navigateToPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("I login with username {string} and password {string}")
    public void login(String user, String pass) {
        try {
            loginPage.login(user, pass);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("I click on Admin tab")
    public void clickAdmin() {
        adminPage.openAdminMenu();
    }

    @When("I get the current number of records")
    public void getRecordCount() {
        oldCount = adminPage.getRecordCount();
    }

    @When("I click on Add button")
    public void clickAdd() {
        adminPage.clickAdd();
    }

    @When("I fill new user details and save")
    public void fillUserDetails() {
        createdUsername = addUserPage.fillUserForm();
    }

    @Then("the number of records should increase by 1")
    public void verifyIncrease() {
        newCount = adminPage.getRecordCount();
        assertEquals(oldCount + 1, newCount);
    }

    @When("I search for the newly added user")
    public void searchUser() {
        adminPage.searchUser(createdUsername);
    }

    @When("I delete the user")
    public void deleteUser() {
        adminPage.deleteUser();
    }

    @Then("the number of records should decrease by 1")
    public void verifyDecrease() {
        int afterDelete = adminPage.getRecordCount();
        assertEquals(newCount - 1, afterDelete);
    }
}
