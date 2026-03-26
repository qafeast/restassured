package tests;

import models.response.UserResponse;
import org.testng.annotations.Test;
import specs.RequestSpec;
import specs.ResponseSpec;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetApiTests {

        @Test
        public void getUserTest() {

                // Send a GET to fetch user 2 using the shared request spec (base URL + headers)
                var httpResponse = given()
                                .spec(RequestSpec.getRequestSpec())
                                .when()
                                .get("/users/2")
                                // Check for HTTP 200 using the shared response spec
                                .then()
                                .spec(ResponseSpec.successResponseSpec())
                                // Capture the full response for printing and mapping
                                .extract()
                                .response();

                // Single println: show the full JSON body in the console
                System.out.println(httpResponse.asPrettyString());

                // Deserialization: map JSON body to UserResponse POJO for easy assertions
                UserResponse response = httpResponse.as(UserResponse.class);

                // Assertions to prove we got the expected user back
                assertEquals(response.getData().getId(), 2);
                assertEquals(response.getData().getFirst_name(), "Janet");
        }
}
