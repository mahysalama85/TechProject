package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddUserPage {

    WebDriver driver;

    @FindBy(xpath = "//label[text()='User Role']/../following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[text()='Admin']")
    WebElement selectAdmin;

    @FindBy(xpath = "//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement statusDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[text()='Enabled']")
    WebElement selectEnabled;


    @FindBy(xpath = "//label[text()='Employee Name']/../following-sibling::div//input")
    WebElement employeeNameInput;

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div//input")
    WebElement usernameInput;

    @FindBy(xpath = "//label[text()='Password']/../following-sibling::div//input")
    WebElement passwordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/../following-sibling::div//input")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement saveBtn;

    public AddUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String fillUserForm() {

        String username = "AutoUser_" + System.currentTimeMillis();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Click User Role dropdown
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectAdmin)).click();
        // Click Status dropdown
        wait.until(ExpectedConditions.elementToBeClickable(statusDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(selectEnabled)).click();

        enterEmployeeName("Test  Employee129");


        wait.until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys("Test@1234");
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordInput)).sendKeys("Test@1234");

        wait.until(ExpectedConditions.visibilityOf(saveBtn)).click();
        //saveBtn.click();

        return username;
    }

    public void enterEmployeeName(String empName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(employeeNameInput)).sendKeys(empName);

        // Wait for dropdown to appear
        WebElement dropdownOption = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@role='option']//span[text()='" + empName + "']")
                )
        );

        // Click the employee from auto-suggest list
        dropdownOption.click();
    }

}