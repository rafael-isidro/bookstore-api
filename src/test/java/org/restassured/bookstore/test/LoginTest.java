package org.restassured.bookstore.test;

import client.LoginClient;
import data.factory.LoginDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.LoginRequestModel;
import models.response.LoginResponseModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.TestListener;

import static story.LoginStory.*;

@Epic(EPIC_LOGIN)
@Story(USER_STORY_LOGIN_POST)
@ExtendWith(TestListener.class)
public class LoginTest {

    public static final String STATUS_SUCCESS = "Success";
    public static final String RESULT_SUCCESS = "User authorized successfully.";
    private final LoginClient loginClient = new LoginClient();

    @Test
    @Description(CT_LOGIN_001)
    public void testValidarLoginComSucesso() {
        LoginRequestModel loginModel = LoginDataFactory.validLogin();

        LoginResponseModel response = loginClient.login(loginModel)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(LoginResponseModel.class);

        Assertions.assertEquals(STATUS_SUCCESS, response.getStatus());
        Assertions.assertEquals(RESULT_SUCCESS, response.getResult());
    }
}
