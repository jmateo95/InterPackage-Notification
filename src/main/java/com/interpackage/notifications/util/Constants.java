/**
 * Detail package info.
 */
package com.interpackage.notifications.util;

/**
 * This class contains constants used throughout the application.
 */
public final class Constants {

    /**
     * The API endpoint for notifications version 1.
     */
    public static final String API_NOTIFICATION_V1 = "/api/notification/v1";

    /**
     * The URL for the frontend.
     */
    public static final String URL_FRONTEND = " ";

    /**
     * The character length for strings with a maximum of 75 characters.
     */
    public static final int LENGTH_75 = 75;

    /**
     * The character length for strings with a maximum of 500 characters.
     */
    public static final int LENGTH_500 = 500;

    /**
     * The default host URL for the application.
     */
    public static final String HOST = "http://localhost:";

    /**
     * The Role Admin.
     */
    public static final String ROLE_ADMIN = "ADMIN";

    /**
     * The Role Operator.
     */
    public static final String ROLE_OPERATOR = "Operator";

    /**
     * The Role Client.
     */
    public static final String ROLE_CLIENT = "CLIENT";

    /**
     * The Role Receptionist.
     */
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
    }
}

