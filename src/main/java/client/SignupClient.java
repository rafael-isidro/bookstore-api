package client;

import io.restassured.http.ContentType;
import models.request.SignupRequestModel;
import models.response.SignupResponseModel;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;

public class SignupClient extends BaseClient {

    private final String REGISTER = "/Account/v1/User";

    public SignupResponseModel register(SignupRequestModel signupModel) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(signupModel)
                .when()
                        .post(REGISTER)
                .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(SignupResponseModel.class);
    }

}
