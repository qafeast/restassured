package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {

    // For successful GET requests
    public static ResponseSpecification successResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    // For successful POST requests
    public static ResponseSpecification createdResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
    }
}