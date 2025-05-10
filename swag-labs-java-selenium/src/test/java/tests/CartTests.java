package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Plan - Cart Functionality Testing:

 * Purpose:
 * - Verify that items can be successfully added to the cart after login.

 * Functional Area:
 * - User Flow: Login → Add Item to Cart → Verify Cart Badge

 * Test Cases:
 * - testAddToCart()

 * Rationale:
 * - This test ensures core e-commerce functionality: the ability to add a product to the shopping cart.
 * - Verifies cart updates correctly when a user adds an item, a key user journey for purchase flows.
 */
public class CartTests extends BaseTest {

    /**
     * Test Case: testAddToCart
     * Scenario: A logged-in user adds one item to the shopping cart.
     * Expected Outcome: The cart badge should display the number "1", indicating one item is present.
     */

    @Test
    public void testAddToCart() {
        // Call the login method to authenticate and log in to the application
        login();
        driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
        String count = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(count, "1");
    }

    /**
     * Helper Method: login
     * Logs in to the Swag Labs application using the standard user credentials.
     * This supports reusability across test cases.
     */
    private void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
}