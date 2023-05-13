/**
 * Este archivo es para controlar los correos
 */
package com.interpackage.notifications.controller;

import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.model.Response;
import com.interpackage.notifications.service.EmailService;
import com.interpackage.notifications.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para manejar las correos electronicos.
 */
@RestController
@RequestMapping(Constants.API_NOTIFICATION_V1 + "/email")
public class EmailController {

    /**
     * `@Autowired` is a Spring annotation that injects an instance
     * of the `EmailService` class into the `emailService` field.
     * This allows the `EmailController` to use the methods
     * provided by the `EmailService` class.
     */
    @Autowired
    private EmailService emailService;
    /** `@Value("${spring.mail.username}")` is a Spring annotation
     * that injects the value of the property `spring.mail.username` from
     * the application properties file into the `mailFrom` field.
     * This allows the `EmailController` to use the email address
     * specified in the application properties file as the sender
     * of the email.
     */ 
    @Value("${spring.mail.username}")
    private String mailFrom;

    /**
     * This method is responsible for handling a POST request
     * to the "/send-email" endpoint and sending an email
     * using the information provided in the request body. It calls the
     * emailService.sendEmail method with the email values
     * and mailFrom address provided in the request body.
     * Returns a ResponseEntity with an HTTP status code and response body
     * indicating the result of the operation.
     * @param dto The EmailValues object containing
     *            the email values provided in the request body
     * @return A ResponseEntity object containing the
     *         result of the operation
     */
    @PostMapping("/send-email")
    public ResponseEntity<Response> sendEmailTemplate(
            final @RequestBody EmailValues dto) {
        return emailService.sendEmail(dto, mailFrom);
    }
}
