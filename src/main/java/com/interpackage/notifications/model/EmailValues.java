package com.interpackage.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailValues {

    private String mailFrom;
    private String mailTo;
    private String subject;
    private String username;
    private String message;
}
