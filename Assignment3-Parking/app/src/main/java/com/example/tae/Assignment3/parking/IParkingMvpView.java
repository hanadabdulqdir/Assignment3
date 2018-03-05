package com.example.tae.Assignment3.parking;

import com.example.tae.Assignment3.data.network.model.Parking;
import com.example.tae.Assignment3.ui.base.MvpView;

import java.util.List;

public interface IParkingMvpView extends MvpView {

    //for the parking locations
    void onFetchDataError(String error);

    void onFetchDataCompleted(List<Parking> parking);

    //for the post reservation
    void onFetchDataCompleted(Parking parking);

}
