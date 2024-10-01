package org.restassured.bookstore.test;

import client.SignupClient;
import data.factory.SignupDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.SignupRequestModel;
import models.response.SignupResponseModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.TestListener;

import static story.SignupStory.*;

@Epic(EPIC_SIGNUP)
@Story(USER_STORY_SIGNUP_POST)
@DisplayName("Endpoint de Registro")
@ExtendWith(TestListener.class)
public class SignupTest {

    private static final String EMPTY_CREDENTIALS_FAIL = "UserName and Password required.";

    private final SignupClient signupClient = new SignupClient();

    @Test
    @Tag("Regression")
    @Tag("Functional")
    @Description(CT_SIGNUP_001)
    public void testValidarRegistroComSucesso() {
        SignupRequestModel signupUser = SignupDataFactory.validRegister();

        SignupResponseModel response = signupClient.register(signupUser)
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract()
                    .as(SignupResponseModel.class);

        Assertions.assertEquals(signupUser.getUserName(), response.getUsername());
        Assertions.assertNotNull(response.getUserID());
    }


    @Test
    @Tag("Regression")
    @Description(CT_SIGNUP_002)
    public void testRegistroComUsernameVazio() {
        SignupRequestModel signupUser = SignupDataFactory.invalidSignupWithEmptyUsername();

        String response = signupClient.register(signupUser)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message")
                    .toString();

        Assertions.assertEquals(EMPTY_CREDENTIALS_FAIL, response);
    }

    @Test
    @Tag("Regression")
    @Description(CT_SIGNUP_003)
    public void testRegistroComSenhaVazia() {
        SignupRequestModel signupUser = SignupDataFactory.invalidSignupWithEmptyPassword();

        String response = signupClient.register(signupUser)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message")
                    .toString();

        Assertions.assertEquals(EMPTY_CREDENTIALS_FAIL, response);

    }
}
