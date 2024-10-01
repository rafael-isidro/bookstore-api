package client;

import data.factory.LoginDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Auth;

import static io.restassured.RestAssured.given;

public class BookClient extends BaseClient {

    private final String BOOKS = "/BookStore/v1/Books";

    private final String AUTHORIZATION = "Authorization";

    public Response getAllBooks() {
        String token = Auth.getToken(LoginDataFactory.validLogin());

        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .header(AUTHORIZATION, token)
                .when()
                        .get(BOOKS)
                ;
    }

}
