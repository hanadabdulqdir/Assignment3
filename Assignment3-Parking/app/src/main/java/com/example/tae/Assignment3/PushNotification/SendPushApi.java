package com.example.tae.Assignment3.PushNotification;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by filippo on 01/03/2018.
 */

public interface SendPushApi {

    @Headers({
            "Content-Type: application/json",
            "Authorization: key=AIzaSyAlkw4MJq1kX5PWbErgySbYGtsEIrFjXHI"
    })
    @POST("/fcm/send")
    Observable<PushResponse> sendPush(@Body PushRequestBody pushRequestBody);
}
