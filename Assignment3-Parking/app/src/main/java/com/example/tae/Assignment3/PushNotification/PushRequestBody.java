package com.example.tae.Assignment3.PushNotification;

/**
 * Created by filippo on 01/03/2018.
 */

public class PushRequestBody {

    private final String to;
    private final Notification notification;

    public PushRequestBody(String to, Notification notification) {
        this.to = to;
        this.notification = notification;
    }
}
