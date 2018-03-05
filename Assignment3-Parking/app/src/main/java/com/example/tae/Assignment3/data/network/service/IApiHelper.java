package com.example.tae.Assignment3.data.network.service;

import com.example.tae.Assignment3.data.network.model.Parking;

import java.util.List;

import io.reactivex.Observable;

public interface IApiHelper {

    Observable<List<Parking>> getLocations();

    Observable<Parking> postReservation(int id);
}
