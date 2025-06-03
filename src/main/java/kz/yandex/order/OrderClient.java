package kz.yandex.order;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static kz.yandex.constants.ApiConstants.SCOOTER_URL;

public class OrderClient {
    protected RequestSpecification requestSpec;

    public OrderClient() {
        RestAssured.baseURI = SCOOTER_URL;

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected RequestSpecification getSpec() {
        return requestSpec;
    }
}