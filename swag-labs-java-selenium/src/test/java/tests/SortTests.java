package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Test Plan - Product Sorting Verification:
 * Purpose:
 * - Verify that the sorting functionality works as expected (Low to High).
 * Functional Area:
 * - UI Sorting: Sorting dropdown for product prices on the inventory page.
 * Test Case:
 * - testSortLowToHigh()
 * Rationale:
 * - Sorting is a key feature in e-commerce applications for user convenience and product discovery.
 * - Ensuring the sort logic displays products in the correct order is critical for user trust and usability.
 */

public class SortTests extends BaseTest {

    /**
     * Test Case: testSortLowToHigh
     * Scenario: A user logs in and applies the "Price (low to high)" sorting option.
     * Expected Outcome: The displayed products should be sorted by ascending price.
     *
     * Steps:
     * 1. Log in as a valid user.
     * 2. Select the "lohi" sort option from the dropdown.
     * 3. Extract all product prices from the page.
     * 4. Verify that the prices are displayed in ascending order.
     */
    @Test
    public void testSortLowToHigh() {
        // Call the login method to authenticate and log in to the application
        login();

        Select sortDropdown = new Select(driver.findElement(By.className("product_sort_container")));
        // Select the sorting option "lohi" (Low to High) from the dropdown
        sortDropdown.selectByValue("lohi");
        // Find all the price elements on the page using the class name "inventory_item_price"
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        List<Double> actual = prices.stream()
                .map(p -> Double.parseDouble(p.getText().replace("$", "")))
                .collect(Collectors.toList());
        List<Double> sorted = actual.stream().sorted().collect(Collectors.toList());
        // Assert that the 'actual' prices list matches the sorted list
        Assert.assertEquals(actual, sorted);
    }
    /**
     * Helper Method: login
     * Logs in to the application using standard user credentials.
     * Promotes code reuse and simplifies test setup.
     */
    private void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
}