package com.swlc.bolton.notifier.constants;

public class ApplicationConstant {
     /**
     * Warnings
     */
    public static final String WARN_PASSWORD_TXT = "Password must contain at least 8 characters, including UPPER/LOWERCASE/SPECIAL_CHARACTER and NUMBERS.";
    public static final String WARN_EMAIL_TXT = "Please enter valid email. Eg: kamal@gmail.com";
    public static final String WARN_ALL_INPUT_REQ = "All input values cannot be empty.";
    
    /**
     * API Response
     */
    public static final String COMMON_SUCCESS_MSG = "Successfully completed.";
    public static final String COMMON_FAILED_MSG = "Failed.";
    public static final String COMMON_UNKNOWN_ERR_MSG = "Something went wrong.";
    public static final String ADDED_EMAIL_ALREADY_EXIST = "Added email already exist.";
    public static final String USER_NOT_FOUND = "Sorry, user not found.";
    public static final String ENTERED_EMAIL_OR_PASSWORD_INVALID = "You've entered an incorrect email/password combination";
    public static final String ALREADY_SUBSCRIBED = "User already subscribed.";

    /**
    * Error codes
    * */
    public static final int BAD_REQUEST = 400;
    public static final int VERIFICATION_FAILED = 101;
    public static final int RESOURCE_NOT_FOUND = 404;
}
