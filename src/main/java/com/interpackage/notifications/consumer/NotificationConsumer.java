package com.interpackage.notifications.consumer;

import com.interpackage.basedomains.dto.UserEvent;
import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.service.EmailService;
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
    public void consume(UserEvent event){
        EmailValues dto = new EmailValues();
        dto.setUsername(event.user.userName);
        dto.setMailTo(event.user.email);
        dto.setSubject("Correo de Bienvenida");
        dto.setMessage("¡Bienvenido al equipo de InterPackageGT " + event.user.name + "!\n\n" +
                "Estamos emocionados de tenerte como parte de nuestro equipo de trabajadores dedicados\n" +
                "al envío de paquetes. Tu dedicación y habilidades serán fundamentales para brindar un\n" +
                "servicio excepcional a nuestros clientes. Juntos, hagamos que cada envío cuente.\n" +
                "\n\n" +
                "¡Bienvenido y éxito en tu carrera con InterPackageGT!");
        emailService.sendEmail(dto, mailFrom);
    }
}
