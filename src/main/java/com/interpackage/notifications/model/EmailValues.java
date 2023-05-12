package com.interpackage.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailValues {

    /** The email address of the sender */
    private String mailFrom;

    /** The email address of the recipient */
    private String mailTo;

    /** The subject of the email */
    private String subject;

    /** The username associated with the email */
    private String username;

    /** The message content of the email */
    private String message;
}
