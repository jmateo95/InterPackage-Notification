package com.interpackage.notifications.interfaces;

import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.model.Response;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface EmailInterface {
    void sendEmailTemplate(EmailValues dto) throws MessagingException;
    ResponseEntity<Response> sendEmail(EmailValues dto, String mailFrom);
}
