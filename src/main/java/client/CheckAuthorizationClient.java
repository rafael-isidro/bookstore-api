package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.LoginRequestModel;

import static io.restassured.RestAssured.given;

public class CheckAuthorizationClient extends BaseClient {

    private final String CHECK_AUTH = "/Account/v1/Authorized";

    public Response checkAuth(LoginRequestModel loginModel) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(loginModel)
                .when()
                        .post(CHECK_AUTH)
                ;
    }

}
