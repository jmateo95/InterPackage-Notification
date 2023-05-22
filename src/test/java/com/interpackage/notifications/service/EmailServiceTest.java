package com.interpackage.notifications.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;
import java.util.UUID;

import com.interpackage.notifications.PostgreSQLExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.interpackage.notifications.interfaces.EmailInterface;
import com.interpackage.notifications.model.EmailValues;
import com.interpackage.notifications.model.Response;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.extension.ExtendWith;

@Testcontainers
@SpringBootTest
@ExtendWith(PostgreSQLExtension.class)
@DirtiesContext
class EmailServiceTest {

    @Autowired
    private EmailInterface emailService;

    private EmailValues emailValues;

    @BeforeEach
    public void setUp() {
        emailValues = new EmailValues();
        emailValues.setMailFrom("test@test.com");
        emailValues.setMailTo("user@test.com");
        emailValues.setSubject("Test Email");
        emailValues.setUsername("User");
        emailValues.setMessage("Test message: " + UUID.randomUUID());
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Delete all test data from the database
    }

    @Test
    void sendEmail() throws Exception {
        ResponseEntity<Response> responseEntity =
                emailService.sendEmail(emailValues, "test@test.com");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Notificación enviada al correo: user@test.com",
                Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }

    @Test
    void sendEmailWithException() throws Exception {
        ResponseEntity<Response> responseEntity =
                emailService.sendEmail(null, "test@test.com");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
