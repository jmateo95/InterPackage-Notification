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
    public static final String ROLE_OPERATOR = "OPERATOR";

    /**
     * The Role Client.
     */
    public static final String ROLE_CLIENT = "Client";

    /**
     * The Role Receptionist.
     */
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    /**
     * The description welcome header.
     */
    public static final String WELCOME_HEADER = "¡Bienvenido a InterPackageGT ";

    /**
     * The description welcome body client.
     */
    public static final String WELCOME_BODY_CLIENT = """
            Somos expertos en la entrega de paquetes. Nuestro compromiso es
            brindarte un servicio confiable, seguro y eficiente. Con nuestro 
            equipo capacitado y una infraestructura sólida, garantizamos que
            tus envíos lleguen a tiempo y en perfectas condiciones.
                        
            Confía en InterPackageGT para una experiencia de entrega excepcional.
            
            ¡Bienvenido(a) a nuestra familia!
            """;

    /**
     * The description welcome body worker.
     */
    public static final String WELCOME_BODY_WORKER = """
        Estamos emocionados de tenerte como parte de nuestro equipo de trabajadores dedicados
        al envío de paquetes. Tu dedicación y habilidades serán fundamentales para brindar un
        servicio excepcional a nuestros clientes. Juntos, hagamos que cada envío cuente.

        ¡Bienvenido y éxito en tu carrera con InterPackageGT!
        """;

    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
    }
}

