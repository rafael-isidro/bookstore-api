package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.LoginRequestModel;
import models.request.SignupRequestModel;
import models.response.LoginResponseModel;
import models.response.SignupResponseModel;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private final String LOGIN = "/Account/v1/GenerateToken";

    public Response login(LoginRequestModel loginModel) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(loginModel)
                .when()
                        .post(LOGIN)
                ;
    }

}
