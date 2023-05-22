/**
 * Este paquete contiene las clases relacionadas
 * con el consumidor de notificaciones.
 * @since 1.0
 * @author BryanGmz
 * @version 1.0
 */
package com.interpackage.notifications.consumer;

import com.interpackage.basedomains.dto.*;
import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.service.EmailService;
import com.interpackage.notifications.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

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
     * Método para consumir eventos de usuario desde Kafka.
     *
     * @param event el evento de usuario recibido desde Kafka
     */
    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.email-consumer.group-id}")
    public void consume(final UserEvent event) {
        EmailValues dto = new EmailValues();
        dto.setUsername(event.user.userName);
        dto.setMailTo(event.user.email);
        dto.setSubject("Correo de Bienvenida");
        dto.setMessage(Constants.WELCOME_HEADER
                + event.user.name + "!\n\n"
                + (event.user.role.equalsIgnoreCase(Constants.ROLE_CLIENT)
                ? Constants.WELCOME_BODY_CLIENT
                : Constants.WELCOME_BODY_WORKER));
        emailService.sendEmail(dto, mailFrom);
    }

    /**
     * Método para consumir eventos de usuario desde Kafka.
     *
     * @param event el evento de usuario recibido desde Kafka
     */
    @KafkaListener(
            topics = "${spring.kafka.route-topic-notification.name}",
            groupId = "${spring.kafka.route-notifications.consumer.group-id}"
    )
    public void routeConsume (final RouteEvent event) {
        EmailValues dto = new EmailValues();
        for (User user : event.getClients()) {
            dto.setUsername(user.userName);
            dto.setMailTo(user.email);
            dto.setSubject("Nueva Ruta");
            dto.setMessage("¡Nueva ruta agregada!\n\n"
                    + "InterPackageGT se enorgullece en anunciar la incorporación de la\n"
                    + "nueva ruta \"" + event.route.getName() + "\". Ahora podrás enviar tus\n"
                    + "paquetes desde \"" + event.route.origin +  "\", hasta \"" + event.route.destination + "\",\n"
                    + "de manera rápida y segura.\n\n"
                    + "Confía en InterPackageGT para la entrega confiable de tus paquetes.\n\n"
                    + "¡Descubre nuestra nueva ruta y disfruta de un servicio excepcional!"
            );
            emailService.sendEmail(dto, mailFrom);
        }
    }

    /**
     * Método para consumir eventos del tracking desde Kafka.
     *
     * @param event el evento de tracking recibido desde Kafka
     */
    @KafkaListener(
            topics = "${spring.kafka.tracking.name}",
            groupId = "${spring.kafka.tracking-notifications.consumer.group-id}"
    )
    public void trackingConsume (final TrackingEvent event) {
        EmailValues dto = new EmailValues();
        dto.setUsername(event.user.userName);
        dto.setMailTo(event.user.email);
        dto.setSubject("Actualización de estado");
        dto.setMessage("¡Actualización de estado de envío!\n\n"
                + "InterPackageGT desea informarte que la orden de envío con número de seguimiento: "
                + event.tracking.trackingNumber + "\n"
                + "ahora se encuentra en: "
                + event.tracking.location + ". Ha llegado a las: \n"
                + event.tracking.checkIn + "."
                + " Confía en InterPackageGT para un servicio de entrega confiable.\n\n");
        emailService.sendEmail(dto, mailFrom);
    }

    /**
     * Método para consumir eventos del qr desde Kafka.
     *
     * @param event el evento de qr recibido desde Kafka
     */
    @KafkaListener(
            topics = "${spring.kafka.qr-topic.name}",
            groupId = "spring.kafka.qr-notifications.group-id"
    )
    public void qrConsume (final QREvent event) {
        EmailValues dto = new EmailValues();
        dto.setUsername(event.getOrder().getClient());
        dto.setMailTo(event.getOrder().getEmail());
        dto.setQrBase64(event.getQr());
        dto.setSubject("Código QR");
        dto.setMessage("¡A continuación se adjunta el código QR de tu envió!");
        emailService.sendEmail(dto, mailFrom);
    }
}
