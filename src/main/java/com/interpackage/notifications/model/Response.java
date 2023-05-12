package com.interpackage.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    
    private String message;
    private Object responseObject;

    public Response(String message){
        this.message = message;
    }

    public Response(Object responseObject) {
        this.responseObject = responseObject;
        this.message = "";
    }
}
