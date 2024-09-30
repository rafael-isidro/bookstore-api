package client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {
    final String BASE_URI = "https://bookstore.toolsqa.com/";

    public RequestSpecification set(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setConfig(RestAssured.config().logConfig(
                        LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .build();
    }
}