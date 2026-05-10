# RESTful Booker Automation Project

This repository contains a multi-module Maven project with automated tests for both API layers of the RESTful Booker system.

## Purpose

The goal of this framework is to ensure reliable regression coverage and high-quality software delivery through automated testing of the API layer.

It focuses on:

- scalable automation design  
- separation of API test layers  
- maintainable test architecture  
- regression stability  

---

## Modules

- [`test-api`](./test-api): API tests using **Java + RestAssured + TestNG**

---

- ## Project Structure

```
restful-booker1/
├── test-api/           # API tests (RestAssured)
├── pom.xml             # Parent POM
└── README.md
```
## Key Design Principles

- Separation of concerns (API only)  
- Reusable and maintainable test structure  
- Modular Maven architecture  
- Clean dependency management  

## Test Strategy

The framework covers:

- REST API CRUD operations validation  
- Positive and negative test scenarios  
- Regression testing of critical API paths  
- Response validation (status codes, payload structure)  
- Cross-layer consistency checks 

## Technologies Used

- Java 17  
- Maven  
- RestAssured  
- TestNG  
- Allure Reports (optional integration) 

## How to Run Tests

### Run API tests
```bash
mvn -pl test-api clean test
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


