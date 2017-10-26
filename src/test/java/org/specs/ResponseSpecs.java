package org.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {

    public final ResponseSpecification getJsonSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();

        builder
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON);

        return builder.build();
    }

}
