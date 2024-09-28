package data.factory;

import models.request.SignupRequestModel;
import net.datafaker.Faker;

public class SignupDataFactory {

    private static Faker faker = new Faker();

    private static SignupRequestModel newRegister(){

        SignupRequestModel register = new SignupRequestModel();

        register.setUserName(faker.name().username());
        register.setPassword(faker.internet().password());

        return register;
    }

    public static SignupRequestModel validRegister(){

        return newRegister();

    }

}
