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

    public static LoginRequestModel invalidLoginWithInvalidPassword(String login){

        LoginRequestModel user = invalidLogin();
        user.setUserName(login);

        return user;
    }

    public static LoginRequestModel invalidLoginWithInvalidEmail(String password){

        LoginRequestModel user = invalidLogin();
        user.setPassword(password);

        return user;
    }

}

