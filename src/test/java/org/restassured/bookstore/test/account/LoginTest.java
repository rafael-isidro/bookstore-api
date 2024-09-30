package org.restassured.bookstore.test.account;

import client.LoginClient;
import data.factory.LoginDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.LoginRequestModel;
import models.response.ResponseModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.TestListener;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static story.LoginStory.*;

@Epic(EPIC_LOGIN)
@Story(USER_STORY_LOGIN_POST)
@ExtendWith(TestListener.class)
public class LoginTest {

    private static final String STATUS_SUCCESS = "Success";
    private static final String STATUS_FAIL = "Failed";

    private static final String RESULT_SUCCESS = "User authorized successfully.";
    private static final String RESULT_AUTHORIZATION_FAIL = "User authorization failed.";
    private static final String EMPTY_CREDENTIALS_FAIL = "UserName and Password required.";

    private final LoginClient loginClient = new LoginClient();

    @Test
    @Description(CT_LOGIN_000)
    @Tag("Contract")
    public void testValidarContratoLogin() {
        LoginRequestModel loginModel = LoginDataFactory.validLogin();

        loginClient.login(loginModel)
                .then()
                    .body(matchesJsonSchemaInClasspath("schemas/login-post.json"));

    }

    @Test
    @Description(CT_LOGIN_001)
    @Tag("Health-Check")
    @Tag("Regression")
    @Tag("Functional")
    public void testValidarLoginComSucesso() {
        LoginRequestModel loginModel = LoginDataFactory.validLogin();

        ResponseModel response = loginClient.login(loginModel)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(ResponseModel.class);

        Assertions.assertEquals(STATUS_SUCCESS, response.getStatus());
        Assertions.assertEquals(RESULT_SUCCESS, response.getResult());
    }

    @Test
    @Tag("Regression")
    @Description(CT_LOGIN_002)
    public void testLoginComDadosInvalidos() {
        LoginRequestModel loginModel = LoginDataFactory.invalidLogin();

        ResponseModel response = loginClient.login(loginModel)
                .then()
                    .extract()
                    .as(ResponseModel.class);

        Assertions.assertNull(response.getToken());
        Assertions.assertEquals(STATUS_FAIL, response.getStatus());
        Assertions.assertEquals(RESULT_AUTHORIZATION_FAIL, response.getResult());
    }

    @Test
    @Tag("Regression")
    @Description(CT_LOGIN_003)
    public void testLoginComSenhaInvalida() {
        LoginRequestModel loginModel = LoginDataFactory.invalidLoginWithInvalidPassword();

        ResponseModel response = loginClient.login(loginModel)
                .then()
                    .extract()
                    .as(ResponseModel.class);

        Assertions.assertNull(response.getToken());
        Assertions.assertEquals(STATUS_FAIL, response.getStatus());
        Assertions.assertEquals(RESULT_AUTHORIZATION_FAIL, response.getResult());
    }

    @Test
    @Tag("Regression")
    @Description(CT_LOGIN_004)
    public void testLoginComUsernameInvalida() {
        LoginRequestModel loginModel = LoginDataFactory.invalidLoginWithInvalidUsername();

        ResponseModel response = loginClient.login(loginModel)
                .then()
                    .extract()
                    .as(ResponseModel.class);

        Assertions.assertNull(response.getToken());
        Assertions.assertEquals(STATUS_FAIL, response.getStatus());
        Assertions.assertEquals(RESULT_AUTHORIZATION_FAIL, response.getResult());
    }

    @Test
    @Tag("Regression")
    @Description(CT_LOGIN_005)
    public void testLoginComUsernameVazio() {
        LoginRequestModel loginModel = LoginDataFactory.invalidLoginWithEmptyUsername();

        String response = loginClient.login(loginModel)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message")
                        .toString();

        Assertions.assertEquals(EMPTY_CREDENTIALS_FAIL, response);
    }

    @Test
    @Tag("Regression")
    @Description(CT_LOGIN_006)
    public void testLoginComSenhaVazio() {
        LoginRequestModel loginModel = LoginDataFactory.invalidLoginWithEmptyPassword();

        String response = loginClient.login(loginModel)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message")
                        .toString();

        Assertions.assertEquals(EMPTY_CREDENTIALS_FAIL, response);
    }


}
