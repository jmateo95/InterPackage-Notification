package com.interpackage.notifications.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interpackage.basedomains.models.GenericEvent;
import com.interpackage.basedomains.models.OrderModel;
import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.OptionalDouble;

@Service
public class OrderConsumer {

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.username}")
    private String mailFrom;


    @KafkaListener(
            topics = "${spring.kafka.topic.order.notification.status.name}",
            groupId = "${spring.kafka.email-consumer.group-id}")
    public void sendInvoiceMail(final GenericEvent ev){
        //var event = (OrderModel )ev.getObject();
        var type = ev.getObject().getClass();
        ObjectMapper objectMapper = new ObjectMapper();
        OrderModel event = objectMapper.convertValue(ev.getObject(), OrderModel.class);
        EmailValues dto = new EmailValues();
        dto.setUsername(event.getClient());
        dto.setMailTo(event.getEmail());
        String subject = "";
        LocalDateTime currentDateTime = LocalDateTime.now();
        String message = "";
        switch (ev.getType()){
            case 1:
                subject = "Envio fallido";
                message = """
                Estimado client: %s
                Hubo un error el enviar la orden a la dirección de %s.
                Motivo: %s
                """.formatted(
                        event.getClient(),
                        event.getFinal_address(),
                        ev.getMessage()
                );
                break;
            case 2:
                subject = "Orden Cancelada";
                message = """
                Estimado client: %s
                Su paquete ha sido cancelado con dirección de %s.
                Fecha y hora: %s
                Motivo: %s
                """.formatted(
                        event.getClient(),
                        event.getFinal_address(),
                        currentDateTime.toString(),
                        ev.getMessage()
                );
                break;
            case 3:
                subject = "Orden Entragada";
                message = """
                Estimado client: %s
                Su paquete ha sido entregada a la dirección de %s.
                Fecha y hora: %s
                """.formatted(
                        event.getClient(),
                        event.getFinal_address(),
                        currentDateTime.toString()
                );
                break;
        }
        dto.setSubject(subject);
        dto.setMessage(message);
        emailService.sendEmail(dto, mailFrom);
    }
}
