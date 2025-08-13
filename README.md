# Random IT Scenario Generator

A Spring Boot REST API that generates a random IT scenario based on user inputs: **Technology**, **Role**, and **Environment**.

## Features
- Accepts user inputs via POST request
- Generates a random scenario with:
  - Challenge
  - Incident
  - Troubleshooting Step
- Follows a **TDD approach** with unit tests
- Written in **Java 8** with **Spring Boot 2.7.x**

## Tech Stack
- Java 8
- Spring Boot
- Maven
- JUnit 5

## Run Locally

```bash
mvn clean package
mvn spring-boot:run
```

The app starts on http://localhost:8080

## API

**POST** `/api/scenario`

**Request body**
```json
{
  "technology": "Cloud Computing",
  "role": "Software Engineer",
  "environment": "Cloud Infrastructure",
  "count": 2
}
```

**Response body (example)**
```json
{
  "technology": "Cloud Computing",
  "role": "Software Engineer",
  "environment": "Cloud Infrastructure",
  "scenarios": [
    {
      "challenge": "Data storage nearing capacity",
      "incident": "High latency for data requests",
      "troubleshootingStep": "Expand storage volume"
    },
    {
      "challenge": "Unexpected API throttling",
      "incident": "Writes failing for analytics pipeline",
      "troubleshootingStep": "Increase request rate limits"
    }
  ]
}

```

## TDD Notes
- Unit test verifies the service generates all fields and echoes inputs.
- You can extend tests to add deterministic randomness via a seeded Random.

## cURL Example
```bash
curl -X POST http://localhost:8080/api/scenario       -H "Content-Type: application/json"       -d '{"technology":"Cloud Computing","role":"Software Engineer","environment":"Cloud Infrastructure"}'
```
