package org.restassured.bookstore.test.account;

import client.CheckAuthorizationClient;
import data.factory.LoginDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.LoginRequestModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.TestListener;

import static story.CheckAuthorizationStory.*;

@Epic(EPIC_CHECK_AUTH)
@Story(USER_STORY_CHECK_AUTH_POST)
@DisplayName("Endpoint de Checagem de Autorização")
@ExtendWith(TestListener.class)
public class CheckAuthorizationTest {

    private final CheckAuthorizationClient checkAuthClient = new CheckAuthorizationClient();


    @Test
    @Tag("Regression")
    @Tag("Functional")
    @Description(CT_CHECK_AUTH_001)
    public void testValidarChecagemAutorizacaoComSucesso() {
        LoginRequestModel login = LoginDataFactory.validLogin();

        String response = checkAuthClient.checkAuth(login)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .asString();

        Assertions.assertEquals("true", response);

    }

    @Test
    @Tag("Regression")
    @Description(CT_CHECK_AUTH_002)
    public void testChecarAutorizacaoDadosInvalidos() {
        LoginRequestModel login = LoginDataFactory.invalidLogin();

        String response = checkAuthClient.checkAuth(login)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .extract()
                    .path("message")
                ;

        Assertions.assertEquals("User not found!", response);

    }

    @Test
    @Tag("Regression")
    @Description(CT_CHECK_AUTH_003)
    public void testChecarAutorizacaoUsernameInvalido() {
        LoginRequestModel login = LoginDataFactory.invalidLoginWithInvalidUsername();

        String response = checkAuthClient.checkAuth(login)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .extract()
                    .path("message")
                ;

        Assertions.assertEquals("User not found!", response);

    }

    @Test
    @Tag("Regression")
    @Description(CT_CHECK_AUTH_004)
    public void testChecarAutorizacaoPasswordInvalido() {
        LoginRequestModel login = LoginDataFactory.invalidLoginWithInvalidPassword();

        String response = checkAuthClient.checkAuth(login)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .extract()
                    .path("message")
                ;

        Assertions.assertEquals("User not found!", response);

    }
}
