package utils;

import client.LoginClient;
import models.request.LoginRequestModel;
import models.response.ResponseModel;

public class Auth {

    private Auth() {}

    private static LoginClient loginClient = new LoginClient();

    public static String getToken(LoginRequestModel loginModel) {

        return loginClient.login(loginModel)
                .then()
                    .extract()
                    .as(ResponseModel.class).getToken();
    }
}
