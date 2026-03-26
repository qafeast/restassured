# Rest Assured + TestNG Starter

API tests against the public ReqRes service, written with Rest Assured, TestNG, and ExtentReports. Tests demonstrate both GET and POST flows with typed POJOs and shared request/response specifications.

## What’s included
- Maven project targeting Java 21.
- Rest Assured 5.4.0 for HTTP calls.
- TestNG 7.11.0 for test orchestration.
- Jackson for (de)serialization of request/response models.
- ExtentReports for HTML reporting.

## Prerequisites
- JDK 21 on your PATH (`java -version` to verify).
- Maven 3.9+ (`mvn -v` to verify).
- Internet access (tests call https://reqres.in).

## How to run
```bash
mvn test
```
What happens:
- Maven Surefire picks up `testng.xml`.
- TestNG executes `tests.GetApiTests` and `tests.PostApiTests`.
- Rest Assured hits ReqRes using the shared `RequestSpec`.
- Assertions validate the deserialized POJOs.
- ExtentReports writes `test-output/extent-report.html`.

## Key files
- `testng.xml` — suite definition and listener wiring.
- `src/test/java/specs/RequestSpec.java` — base URI, base path, content type, API key header.
- `src/test/java/specs/ResponseSpec.java` — reusable expectations for 200/201.
- `src/test/java/tests/GetApiTests.java` — GET `/users/2`, validates id and first name.
- `src/test/java/tests/PostApiTests.java` — POST `/users`, validates echoed fields plus generated id/timestamp.
- `src/test/java/models/request/CreateUserRequest.java` — request body POJO, serialized to JSON for the POST call.
- `src/test/java/models/response/*` — response POJOs with `@JsonIgnoreProperties`; Rest Assured deserializes JSON into these for assertions.
- `src/test/java/reports/TestListener.java` + `ExtentManager.java` — ExtentReports setup.

## How models are used
- Request models (`models/request`) are plain POJOs populated in the test (`PostApiTests#createUserTest`) and sent as the request body; Rest Assured serializes them to JSON automatically.
- Response models (`models/response`) mirror the expected JSON. After each call, `response.as(...)` maps the payload into these classes, making field-level assertions straightforward in both GET and POST tests.

## Reports
- After a run, open `test-output/extent-report.html` in a browser for the styled report.
- Raw console output shows the prettified JSON bodies for quick inspection.

## Execution path (text chart)
```
Developer
  |
  | mvn test
  v
Maven Surefire
  |
  | loads testng.xml
  v
TestNG Suite
  |
  | runs GetApiTests / PostApiTests
  v
Rest Assured client
  |
  | uses RequestSpec + ResponseSpec
  v
ReqRes API (HTTPS)
  |
  | JSON response
  v
Jackson deserialization -> POJOs
  |
  | assertions (TestNG)
  v
ExtentReports -> test-output/extent-report.html
```
