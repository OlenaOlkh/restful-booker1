# RESTful Booker Automation Project

This repository contains a multi-module Maven project with automated tests for both API and UI layers of the RESTful Booker system.

## Purpose

The goal of this framework is to ensure reliable regression coverage and high-quality software delivery through automated testing of both API and UI layers.

It focuses on:

- scalable automation design  
- separation of API and UI test layers  
- maintainable test architecture  
- regression stability  

## Modules

- [`test-api`](./test-api): API tests using **Java + RestAssured + TestNG**  
- [`test-ui`](./test-ui): UI tests using **Java + Selenide + TestNG**

- ## Project Structure

```
restful-booker1/
├── test-api/           # API tests (RestAssured)
├── test-ui/            # UI tests (Selenide)
├── pom.xml             # Parent POM
└── README.md
```
## Key Design Principles

- Separation of concerns (API vs UI)  
- Reusable and maintainable test structure  
- Modular Maven architecture  
- Clean dependency management  

## Test Strategy

The framework covers:

- REST API CRUD operations validation  
- Positive and negative test scenarios  
- UI validation of booking flows  
- Regression testing of critical application paths  
- Response validation (status codes, payload structure)  
- Cross-layer consistency checks  

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

## Author

**Olena Olkhovska**  
QA Automation Engineer  
Berlin, Germany  
LinkedIn: https://www.linkedin.com/in/olena-olkhovska/

---


