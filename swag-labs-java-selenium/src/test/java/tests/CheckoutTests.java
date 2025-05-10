package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
/**
 * Test Plan - Checkout Validation:

 * Purpose:
 * - Validate form input behavior during the checkout process.

 * Functional Area:
 * - User Flow: Login → Add to Cart → Start Checkout → Attempt to Proceed Without Filling Form

 * Test Case:
 * - testCheckoutValidation()

 * Rationale:
 * - Ensures that the system enforces required fields (e.g., First Name) in the checkout form.
 * - Simulates a user attempting to proceed with incomplete data and expects validation messaging.
 */

public class CheckoutTests extends BaseTest {

    /**
     * Test Case: testCheckoutValidation
     * Scenario: User tries to proceed through checkout without entering required form data.
     * Expected Outcome: System displays a validation error ("First Name is required").
     *
     * Steps:
     * 1. Log in and add an item to the cart.
     * 2. Go to the cart and start the checkout.
     * 3. Click 'Continue' without entering any information.
     * 4. Verify that the appropriate error message is shown.
     */

    @Test
    public void testCheckoutValidation() throws InterruptedException {
        loginAndAddToCart();

        driver.findElement(By.className("shopping_cart_link")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("cart.html"));
        Assert.assertTrue(driver.findElements(By.className("cart_item")).size() > 0, "Cart is empty!");

        // Use fallback XPath to avoid selector failures
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='btn_action checkout_button'] | //button[@id='checkout']")));
        checkoutBtn.click();
        driver.findElement(By.id("continue")).click();
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h3[data-test='error']")));
        String error = errorElement.getText();

        Assert.assertTrue(error.contains("First Name is required"), "Expected validation error not found. Found: " + error);
    }


    /**
     * Helper Method: loginAndAddToCart
     * Logs in to the application using valid credentials and adds one product to the cart.
     * Promotes reuse across test cases and reduces code duplication.
     */
    private void loginAndAddToCart() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]")).click();
    }
}