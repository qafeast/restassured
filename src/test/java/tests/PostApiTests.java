package tests;

import models.request.CreateUserRequest;
import models.response.CreateUserResponse;
import org.testng.annotations.Test;
import specs.RequestSpec;
import specs.ResponseSpec;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

public class PostApiTests {

        @Test
        public void createUserTest() {

                // Prepare the request body with the fields the API expects
                CreateUserRequest request = new CreateUserRequest();
                request.setName("morpheus");
                request.setJob("leader");

                // Serialization: RestAssured converts the request POJO to JSON
                var response = given()
                                .spec(RequestSpec.getRequestSpec())
                                .body(request)
                                .when()
                                .post("/users")
                                // Expect HTTP 201 Created
                                .then()
                                .spec(ResponseSpec.createdResponseSpec())
                                .extract()
                                .response();

                System.out.println(response.asString());

                // Deserialization: map JSON response to CreateUserResponse POJO
                CreateUserResponse userResponse = response.as(CreateUserResponse.class);

                // Basic sanity checks to prove serialization/deserialization worked
                assertEquals(userResponse.getName(), request.getName());
                assertEquals(userResponse.getJob(), request.getJob());
                // The API generates an id and timestamp; just assert they are present
                assertFalse(userResponse.getId().isEmpty(), "id should be generated");
                assertFalse(userResponse.getCreatedAt().isEmpty(), "createdAt should be generated");
        }
}
