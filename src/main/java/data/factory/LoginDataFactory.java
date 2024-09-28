package data.factory;

import models.request.LoginRequestModel;
import net.datafaker.Faker;
import utils.Manipulation;

import java.util.Locale;

public class LoginDataFactory {

    public static Faker faker = new Faker(new Locale("PT-BR"));
    public static final String USER_LOGIN_PROP = "USER_LOGIN";
    public static final String USER_PSW_PROP = "USER_PSW";

    public static LoginRequestModel validLogin(){

        LoginRequestModel user = new LoginRequestModel();

        user.setUserName(Manipulation.getProp().getProperty(USER_LOGIN_PROP));
        user.setPassword(Manipulation.getProp().getProperty(USER_PSW_PROP));

        return user;

    }

    public static LoginRequestModel invalidLogin(){

        LoginRequestModel user = new LoginRequestModel();

        user.setUserName(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());

        return user;
    }

    public static LoginRequestModel invalidLoginWithInvalidPassword(){

        LoginRequestModel user = invalidLogin();
        user.setUserName(USER_LOGIN_PROP);

        return user;
    }

    public static LoginRequestModel invalidLoginWithInvalidUsername(){

        LoginRequestModel user = invalidLogin();
        user.setPassword(USER_PSW_PROP);

        return user;
    }

    public static LoginRequestModel invalidLoginWithEmptyUsername(){

        LoginRequestModel user = new LoginRequestModel();
        user.setPassword(USER_PSW_PROP);

        return user;
    }

    public static LoginRequestModel invalidLoginWithEmptyPassword(){

        LoginRequestModel user = new LoginRequestModel();
        user.setUserName(USER_LOGIN_PROP);

        return user;
    }

}

