package com.example.tae.Assignment3.data.network.service;

import com.example.tae.Assignment3.data.network.model.Parking;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface IRequestInterface {

    @GET(ApiList.LOCATION_DETAILS)
    Observable<List<Parking>> getLocations();

    @POST(ApiList.RESERVE_URL)
    Observable<Parking> postReserveParking(@Path("id") int id);


}
