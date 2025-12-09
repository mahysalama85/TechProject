package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class AdminPage {

    WebDriver driver;

    @FindBy(xpath = "//span[text()='Admin']")
            ////*[@id="app"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a
    WebElement adminMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addButton;

    @FindBy(xpath = "//div[@class='oxd-table-card']")
    List<WebElement> recordsList;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement searchUsernameInput;

    @FindBy(xpath = "//button[contains(@class,'oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space')]")
    WebElement searchBtn;

    @FindBy(css = "button.oxd-icon-button.oxd-table-cell-action-space")
    WebElement deleteBtn;

    @FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
    WebElement confirmDeleteBtn;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openAdminMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(adminMenu)).click();
    }

    public int getRecordCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        List<WebElement> rows = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[@class='oxd-table-body']/div")
                )
        );
        return rows.size();
    }

    public void clickAdd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
    }

    public void searchUser(String username) {
        searchUsernameInput.clear();
        searchUsernameInput.sendKeys(username);
        searchBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void deleteUser() {
        deleteBtn.click();
        confirmDeleteBtn.click();
    }
}
