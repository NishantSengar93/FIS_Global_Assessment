# Automation Testing Framework

This project is a comprehensive automation testing framework designed to test UI and API functionalities. It supports multiple browsers and integrates professional reporting and logging.

## Features

1. **UI Automation**

    - Automates e-commerce workflows (e.g., adding items to the cart on eBay).
    - Supports browser-specific configurations using WebDriverManager.
    - Handles advanced scenarios, such as switching between browser tabs.

2. **API Automation**

    - Verifies RESTful API endpoints.
    - Validates JSON responses using schema validation.

3. **Extensive Reporting**

    - Generates a single detailed ExtentReport (`Test_Execution_Report.html`) for both UI and API tests when executed together.
    - Embeds screenshots inline with logs for easy debugging.

4. **Reusable Utilities**

    - DriverManager for browser-specific WebDriver initialization.
    - Logging with Log4j.
    - Screenshot capturing as Base64 for embedding in reports.

5. **Cross-Browser Support**

    - Chrome, Firefox, Edge, and Safari.

---

## Project Structure

```
src
├── main
│   ├── java
│   │   └── utils
│   │       ├── DriverManager.java
│   │       ├── ExtentManager.java
│   │       ├── LoggerUtil.java
│   │       └── ScreenshotUtil.java
├── test
│   ├── java
│   │   ├── ui
│   │   │   └── AddToCartTest.java
│   │   ├── api
│   │   │   └── CoinDeskAPITest.java
│   │   └── pages
│   │       ├── HomePage.java
│   │       ├── SearchResultsPage.java
│   │       └── CartPage.java
│   └── resources
│       ├── ui-tests.xml
│       ├── api-tests.xml
│       └── tests.xml
```

---

## Prerequisites

1. **Java**: Ensure JDK 8 or higher is installed.
2. **Maven**: Install Maven for project build and dependency management.
3. **ChromeDriver/GeckoDriver**: Managed automatically by WebDriverManager.
4. **Dependencies**: All required libraries are specified in the `pom.xml` file.

---

## Key Dependencies

- **Selenium WebDriver**: For UI automation.
- **RestAssured**: For API testing.
- **WebDriverManager**: For managing browser drivers.
- **ExtentReports**: For professional test reporting.
- **Log4j**: For detailed logging.

---

## Configuration

### Browser Configuration

By default, the tests run on Google Chrome. To specify a different browser, pass the `browser` parameter via the command line.

```bash
mvn test -Dbrowser=firefox
```

Alternatively, specify the browser in the TestNG XML file using the `parameter` tag:

#### Example `ui-tests.xml`

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="UI Tests" parallel="false">
    <parameter name="browser" value="chrome" />
    <test name="UI Automation">
        <classes>
            <class name="ui.AddToCartTest"/>
        </classes>
    </test>
</suite>
```

The browser parameter will be passed dynamically to the test.

### TestNG Suite Files

1. **UI Tests**: `ui-tests.xml`
2. **API Tests**: `api-tests.xml`
3. **Combined Tests**: `tests.xml`

#### Example `tests.xml`

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Combined Tests" parallel="false">
    <parameter name="browser" value="chrome" />
    <test name="UI Automation">
        <classes>
            <class name="ui.AddToCartTest"/>
        </classes>
    </test>
    <test name="API Automation">
        <classes>
            <class name="api.CoinDeskAPITest"/>
        </classes>
    </test>
</suite>
```

---

## Execution Steps

### Step 1: Clone the Repository

```bash
git clone <repository_url>
cd <repository_folder>
```

### Step 2: Install Dependencies

Run the following command to download all dependencies:

```bash
mvn clean install
```

### Step 3: Run Tests

#### Run UI Tests

```bash
mvn test -DsuiteXmlFile=src/test/resources/ui-tests.xml
```

#### Run API Tests

```bash
mvn test -DsuiteXmlFile=src/test/resources/api-tests.xml
```

#### Run Combined Tests

```bash
mvn test -DsuiteXmlFile=src/test/resources/tests.xml
```

#### Specify Browser (Optional)

```bash
mvn test -DsuiteXmlFile=src/test/resources/ui-tests.xml -Dbrowser=edge
```

---

## Reports

### Extent Reports

After test execution, a single ExtentReport is generated in the `reports` directory:

- **Combined Tests Report**: `target/reports/Test_Execution_Report.html`

This report includes both UI and API test results when executed together using the `tests.xml` suite file.

### Screenshots

Screenshots are embedded inline with the logs in the reports for easy debugging.

---

## Utilities

### DriverManager

Centralized utility for managing WebDriver instances. Supports Chrome, Firefox, Edge, and Safari. Accepts browser parameters dynamically from the TestNG XML file or command-line arguments.

### ExtentManager

Generates a single ExtentReport for all executed tests.

### ScreenshotUtil

Captures screenshots as Base64 strings for embedding in ExtentReports.

### LoggerUtil

Integrates Log4j for detailed, configurable logging.

---

## Contribution Guidelines

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes with descriptive messages.
4. Open a pull request for review.

---

## Contact

For questions or support, please reach out to the repository owner at [[nishantsengar13@gmail.com](mailto\:email@example.com)].

