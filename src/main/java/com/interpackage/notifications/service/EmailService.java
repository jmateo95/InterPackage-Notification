/**
 * Packete para menejar los servicios
 */
package com.interpackage.notifications.service;

import com.interpackage.notifications.interfaces.EmailInterface;
import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.model.Response;
import com.interpackage.notifications.util.Constants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService implements EmailInterface {

    /**
     * The JavaMailSender used for sending emails.
     */
    @Autowired
    private JavaMailSender javaMailSender;
    /**
     * The TemplateEngine used for generating email templates.
     */
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * Sends an email using a template with the given email values.
     * @param dto the EmailValues object containing the email details.
     * @throws MessagingException if an error occurs while
     * sending the email.
     */
    public void sendEmailTemplate(
            final EmailValues dto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        Context context = new Context();
        Map<String, Object> model = new HashMap<>();
        model.put("username", dto.getUsername());
        model.put("url", Constants.URL_FRONTEND);
        model.put("message", dto.getMessage());
        context.setVariables(model);
        String htmlText = templateEngine
                .process("email-template", context);
        helper.setFrom(dto.getMailFrom());
        helper.setTo(dto.getMailTo());
        helper.setSubject(dto.getSubject());
        helper.setText(htmlText, true);
        javaMailSender.send(message);
    }

    /**
     * Sends an email using the provided email values and
     * the specified sender email.
     * @param dto the email values to send
     * @param mailFrom the sender email
     * @return a response entity indicating if the email was
     * sent successfully or not
     */
    public ResponseEntity<Response> sendEmail(
            final EmailValues dto, final String mailFrom) {
        try {
            dto.setMailFrom(mailFrom);
            sendEmailTemplate(dto);
            Map<String, Object> map = new HashMap<>();
            return new ResponseEntity<>(
                    new Response("Notificación enviada al correo: "
                            + dto.getMailTo(), map), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .build(); // 400 Bad Request
        }
    }
}
