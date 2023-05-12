/**
 * Detail package info.
 */
package com.interpackage.notifications.interfaces;

import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.model.Response;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface EmailInterface {

    /**
     * Sends an email using a template with the given email values.
     * @param dto the EmailValues object containing the email details.
     * @throws MessagingException if an error occurs while sending
     *                            the email.
     */
    void sendEmailTemplate(EmailValues dto) throws MessagingException;
    /**
     * Sends an email using the provided email values and
     * the specified sender email.
     * @param dto the email values to send
     * @param mailFrom the sender email
     * @return a response entity indicating if the email was
     * sent successfully or not
     */
    ResponseEntity<Response> sendEmail(EmailValues dto, String mailFrom);
}
