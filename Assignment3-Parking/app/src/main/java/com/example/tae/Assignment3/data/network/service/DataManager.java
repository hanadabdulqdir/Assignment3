package com.example.tae.Assignment3.data.network.service;

import com.example.tae.Assignment3.data.network.model.Parking;

import java.util.List;

import io.reactivex.Observable;


public class DataManager implements IDataManager {


    private IApiHelper iApiHelper;

    public DataManager() {
        this.iApiHelper = new ApiHelper();
    }

    @Override
    public Observable<List<Parking>> getLocations() {
        return iApiHelper.getLocations();
    }

    @Override
    public Observable<Parking> postReservation(int id) {
        return iApiHelper.postReservation(id);
    }
}

