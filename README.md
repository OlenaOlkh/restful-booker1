# RESTful Booker Automation Project

This repository contains a multi-module Maven project with automated tests for both API and UI layers of the RESTful Booker system.

## Modules

- [`test-api`](./test-api): API tests using Java, RestAssured, and TestNG.
- [`test-ui`](./test-ui): UI tests using Java, Selenide, and TestNG.

## Technologies Used

- Java 17
- Maven
- RestAssured
- Selenide
- TestNG
- Allure Reports (optional integration)
- Page Object Pattern (for UI tests)

## How to Run Tests

### Run API tests
```bash
mvn -pl test-api clean test
```

### Run UI tests
```bash
mvn -pl test-ui clean test
```

### Run all modules
```bash
mvn clean install
```

### Generate Allure Report (optional)
```bash
allure serve target/allure-results
```
```bash
allure serve test-api/target/allure-results
```

> Make sure you have Allure CLI installed: https://docs.qameta.io/allure/#_installing_a_commandline

## Project Structure

```
restful-booker1/
├── test-api/           # API tests (RestAssured)
├── test-ui/            # UI tests (Selenide)
├── pom.xml             # Parent POM
└── README.md
```

## Author

**Olena Olkhovska**  
QA Automation Engineer  
Berlin, Germany  
LinkedIn: https://www.linkedin.com/in/olena-olkhovska/

---


