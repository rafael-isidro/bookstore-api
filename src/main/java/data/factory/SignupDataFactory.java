package data.factory;

import models.request.SignupRequestModel;
import net.datafaker.Faker;
import utils.Manipulation;

public class SignupDataFactory {

    public static final String INVALID_USERNAME = "###";
    public static final String INVALID_PASSWORD = "1";
    public static final String USER_LOGIN_PROP = "USER_LOGIN";

    private static Faker faker = new Faker();

    private static SignupRequestModel newRegister(){

        SignupRequestModel register = new SignupRequestModel();

        register.setUserName(faker.name().username());
        register.setPassword(faker.internet().password() + "A#");

        return register;
    }

    public static SignupRequestModel invalidSignupWithEmptyUsername(){

        SignupRequestModel user = new SignupRequestModel();
        user.setPassword(faker.internet().password());

        return user;
    }

    public static SignupRequestModel invalidSignupWithEmptyPassword(){

        SignupRequestModel user = new SignupRequestModel();
        user.setUserName(faker.internet().emailAddress());

        return user;
    }

    public static SignupRequestModel invalidSignupWithExistingUsername(){

        SignupRequestModel user = validRegister();
        user.setUserName(Manipulation.getProp().getProperty(USER_LOGIN_PROP));

        return user;
    }

    public static SignupRequestModel validRegister(){
        return newRegister();
    }
}
