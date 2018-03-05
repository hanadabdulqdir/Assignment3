package com.example.tae.Assignment3.data.network.service;

import com.example.tae.Assignment3.data.network.model.Parking;

import java.util.List;

import io.reactivex.Observable;


public class ApiHelper implements IApiHelper {

    private IRequestInterface iRequestInterface;

    public ApiHelper() {
        this.iRequestInterface = ServiceConnection.getConnection();
    }

    @Override
    public Observable<List<Parking>> getLocations()
    {
        return iRequestInterface.getLocations();
    }

    @Override
    public Observable<Parking> postReservation(int id) {
        return iRequestInterface.postReserveParking(id);
    }
}
