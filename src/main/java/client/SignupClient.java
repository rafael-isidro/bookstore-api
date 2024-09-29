package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.SignupRequestModel;

import static io.restassured.RestAssured.given;

public class SignupClient extends BaseClient {

    private final String REGISTER = "/Account/v1/User";

    public Response register(SignupRequestModel signupModel) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(signupModel)
                .when()
                        .post(REGISTER)
                ;
    }

}
