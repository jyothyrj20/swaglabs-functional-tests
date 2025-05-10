
# Swag Labs Automation - Senior Test Engineer Assessment

## Objective
This project is designed to demonstrate functional test automation for the Swag Labs demo application ([https://www.saucedemo.com] The goal is to validate key workflows and ensure consistent user experience through automated testing.

## Tools and Frameworks Used
- **Language:** Java
- **Framework:** Selenium WebDriver with TestNG
- **Build Tool:** Maven
- **Browser Drivers:** Managed using WebDriverManager
- **IDE:** IntelliJ IDEA or Eclipse

## Test Structure
The following test classes are included:

- `LoginTests.java` - Valid and locked-out user login scenarios
- `CartTests.java` - Adding items to the cart and cart counter validation
- `CheckoutTests.java` - Checkout form validation and error handling
- `SortTests.java` - Sorting products by price (low to high) and validating order

## How to Run Tests

1. Clone this repository or download the code.
2. Open in IntelliJ IDEA or Eclipse.
3. Ensure you have Maven installed and configured.
4. Run the tests using the Maven command:

```bash
mvn clean test
```

## Notes

- Ensure a stable internet connection as the tests rely on a publicly hosted demo site.
- All tests use the default user `standard_user` with password `secret_sauce`.

- ##  Automated Test Cases

| Test Class       | Scenario Tested                                | Reason for Automation                      |
|------------------|-------------------------------------------------|--------------------------------------------|
|  LoginTests     | Valid login and locked user                    | Ensures access control and error handling  |
|  CartTests      | Add item to cart and validate count            | Core e-commerce action                     |
|  CheckoutTests  | Validate required fields in checkout form      | Critical for form validation               |
|  SortTests      | Ensure product sort low-to-high works properly | Enhances product discoverability           |


## Issues Encountered

**Issue 1: Locked-out User Login Message Is Lost After Refresh
Steps to Reproduce:
1.Login with locked_out_user.
2.Observe error message.
3.Refresh the page.

Expected:
Error message should persist or reappear.

Actual:
Error disappears entirely.

Adjustment:
Add assertions post-refresh or test consistency by retrying login after refresh. Also log state change for further inspection.

** Issue 2: Checkout Form – Only One Field Error at a Time
Steps to Reproduce:
1.Go to checkout without entering any form data.
2.Click Continue.

Expected:
All missing fields should be highlighted together.

Actual:
Only the first missing field ("First Name") triggers a validation error.

Adjustment:
Parametrize tests to submit one missing field at a time for thorough validation.

** Issue 3: Sorting Low to High Not Always Correct
Steps to Reproduce:
1.Login as standard user.
2.Sort products "Low to High".

Expected:
Product prices are sorted in ascending order.

Actual:
Occasionally, items are out of order visually or due to page load delays.

Adjustment:
Add waits before asserting. Compare the full list of price values programmatically.

 **Issue 4: No Feedback When Cart Is Empty
Steps to Reproduce:
1.Go to cart without adding any items.

Expected:
A message like "Your cart is empty" should appear.

Actual:
Cart appears blank without any feedback.

Adjustment:
Submit this as a UI/UX improvement suggestion and assert the absence of cart items instead.

**Issue 5: Accessibility Violations
Steps to Reproduce:
1.Open the login or checkout error messages.
2.Use an accessibility audit tool (e.g., Axe or Lighthouse).

Expected:
Error messages should be labeled with ARIA attributes for screen readers.

Actual:
No ARIA labels found; screen readers may not detect the error.

Adjustment:
Include accessibility testing tools in the CI pipeline and raise it as a non-functional bug.
