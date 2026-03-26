package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    // Common request configuration for ReqRes APIs
    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()

                // Base URL
                .setBaseUri("https://reqres.in")

                // Base path
                .setBasePath("/api")

                // Default content type
                .setContentType("application/json")

                // Passing API key as a header
                .addHeader("x-api-key", "reqres_521e5021d6d340289ccd34d29467f4d1")

                .build();
    }
}