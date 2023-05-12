/**
 * Detail package info.
 */
package com.interpackage.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    /** The message associated with the response. */
    private String message;
    /** The object representing the response. */
    private Object responseObject;

    /**
     * Class constructor specifying a message.
     * @param message the message to be set for the response
     */
    public Response(final String message){
        this.message = message;
    }

    /**
     * Class constructor specifying a response object.
     * @param responseObject the response object to be set
     *                       for the response
     */
    public Response(final Object responseObject) {
        this.responseObject = responseObject;
        this.message = "";
    }
}
