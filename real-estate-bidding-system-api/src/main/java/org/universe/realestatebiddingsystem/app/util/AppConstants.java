package org.universe.realestatebiddingsystem.app.util;

public interface AppConstants {
    String USER_CAPITALIZED = "User";
    String EMAIL = "email";

    String ADMIN = "admin";
    String FIELD = " field ";
    String DOT = ".";
    int ZERO = 0;
    int ONE = 1;
    String DASH = "-";

    //    AUTH
    String IS_AUTHENTICATED = "isAuthenticated()";
    String IS_ADMIN = "hasRole('ROLE_ADMIN')";

    //    URLS
    String HOME_URL = "/";
    String ID_PATTERN_URL = "/{id}";
    String USERS_URL = "/users";
    String ADMIN_URL = "/admin";
    String PECULIARITY_URL = "/peculiarity";
    String ESTATES_URL = "/estates";
    String NEW_URL = "/new";
    String USERS_URL_ALL = "/users/**";
    String AUTH_ALL_URL = "/auth/**";
    String MAP_ALL_URL = "/**";
    String ALL_URL = "/all";
    String CITIES_URL = "/cities";
    String TYPES_URL = "/types";
    String PECULIARITIES_URL = "/peculiarities";
    String CURRENT_USER_URL = "/current";
    String REGISTER_URL = "/register";
    String LOGIN_URL = "/login";
    String USERS_DETAILS_ID_URL = "/details/{id}";

    //    MESSAGES
    String RESPONDING_WITH_UNAUTHORIZED_ERROR_MESSAGE = "Responding with unauthorized error. Message - {}";
    String SORRY_YOU_RE_NOT_AUTHORIZED_TO_ACCESS_THIS_RESOURCE_MESSAGE = "Sorry, You're not authorized to access this resource.";
    String INVALID_CREDENTIALS_MESSAGE = "Invalid credentials.";
    String INVALID_PASSWORD_MESSAGE = "Invalid or mismatch password.";
    String COULD_NOT_SET_USER_AUTHENTICATION_IN_SECURITY_CONTEXT_MESSAGE = "Could not set user authentication in security context";
    String AUTHORIZATION = "Authorization";
    String AUTHORIZATION_LOWERCASE = "Authorization";
    String EMTPY = "";

    String USER_REGISTERED_SUCCESSFULLY_MESSAGE = "%s registered successfully.";
    String USER_LOGGED_SUCCESSFULLY_MESSAGE = "%s logged in successfully.";
    String PROFILE_EDITED_SUCCESSFULLY_MESSAGE = "Profile edited successfully.";
    String PROFILE_DISABLED_SUCCESSFULLY_MESSAGE = "Profile disabled successfully.";
    String ESTATE_ADDED_SUCCESSFULLY_MESSAGE = "Estate added successfully.";
    String INVALID_TOWN_MESSAGE = "Invalid town provided.";
    String INVALID_PHONE_MESSAGE = "Invalid phone provided.";
    String INVALID_FIRST_NAME_MESSAGE = "Invalid first name provided.";
    String INVALID_CONFIRM_PASSWORD_MESSAGE = "Custom password field is mandatory.";
    String INVALID_LAST_NAME_MESSAGE = "Invalid last name provided.";
    String EMAIL_ADDRESS_ALREADY_IN_USE_MESSAGE = "Email Address already in use!";
    String PASSWORDS_MISMATCH_MESSAGE = "Passwords do not match!";
    String USER_ROLE_NOT_SET_MESSAGE = "User Role not set.";

    //    PreAuthorize params
    String HAS_ROLE_USER = "hasRole('USER_UPPERCASE')";
    String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
    String HAS_ANY_ROLE_AUTH = "hasAnyRole('USER_UPPERCASE', 'ADMIN')";

    //    JWT
    String APP_JWT_SECRET = "${app.jwtSecret}";
    String APP_JWT_EXPIRATION_IN_MS = "${app.jwtExpirationInMs}";
    String INVALID_JWT_SIGNATURE = "Invalid JWT signature";
    String INVALID_JWT_TOKEN = "Invalid JWT token";
    String EXPIRED_JWT_TOKEN = "Expired JWT token";
    String UNSUPPORTED_JWT_TOKEN = "Unsupported JWT token";
    String JWT_CLAIMS_STRING_IS_EMPTY = "JWT claims string is empty.";
    String BEARER_ = "Bearer ";

    //    CONSTANTS
    String TELEPHONE_REGEXP = "(^$|[0-9]{10})";
    int TELEPHONE_LENGHT = 10;
    int ROLE_NAME_LENGH_VALUE = 20;
    String USERS = "users";
    String ESTATES = "estates";
    String ROLES = "roles";
    String TYPES = "types";
    String PECULIARITIES = "peculiarities";
    String CITIES = "cities";
    int USER_FIRST_NAME_MAX_VALUE = 30;
    int EMAIL_MAX_VALUE = 40;
    int EMAIL_MIN_VALUE = 8;
    String USER_ROLES = "user_roles";
    String USER_ID = "user_id";
    String ROLE_ID = "role_id";
    String USER_NOT_FOUND_WITH_EMAIL_MESSAGE = "User not found with email: ";
    String USER_NOT_FOUND_WITH_ID_MESSAGE = "User not found with id: ";
    String BANNED_USER_MESSAGE = "User was banned from admin.";
    String ID = "id";
    int PASSWORD_MAX_VALUE = 20;
    int PASSWORD_MIN_VALUE = 6;
    int USER_FIRST_NAME_MIN_VALUE = 3;
    int TOWN_MIN_VALUE = 3;
    int TOWN_MAX_VALUE = 30;
    int USER_LAST_NAME_MIN_VALUE = 3;
    int USER_LAST_NAME_MAX_VALUE = 30;
}
