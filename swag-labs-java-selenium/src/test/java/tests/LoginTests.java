package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTests extends BaseTest {

    /*
      Test Plan - Login Functional Testing:
      - Verify login with valid credentials (standard user).
      - Verify login with locked user credentials.

      Test Cases:
      - testValidLogin()
      - testLockedUserLogin()
     */

    /**
     * Test Case: testValidLogin
     * Scenario: User logs in with valid credentials.
     * Expected Outcome: User should be redirected to the inventory page.
     * Rationale: This test ensures that the application allows valid users to login successfully
     * and is redirected to the appropriate page.
     */

    @Test
    public void testValidLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    /**
     * Test Case: testLockedUserLogin
     * Scenario: User attempts to log in with a locked user account.
     * Expected Outcome: An error message should be displayed indicating the account is locked.
     * Rationale: This test ensures that the system properly handles login attempts from locked accounts,
     * preventing access and displaying an appropriate error message.
     */
    @Test
    public void testLockedUserLogin() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        );

        String errorText = errorMessage.getText();
        System.out.println("Error message shown: " + errorText);
        Assert.assertTrue(errorText.toLowerCase().contains("sorry, this user has been locked out"));
    }
}