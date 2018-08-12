package org.universe.realestatebiddingsystem.app.util;

import com.google.gson.Gson;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.USER_REGISTERED_SUCCESSFULLY_MESSAGE;

public final class Json {

    public static String toJson(Object object, String email) {
        return new Gson().toJson(String.format(USER_REGISTERED_SUCCESSFULLY_MESSAGE, email));
    }

    private Json() {
    }
}
