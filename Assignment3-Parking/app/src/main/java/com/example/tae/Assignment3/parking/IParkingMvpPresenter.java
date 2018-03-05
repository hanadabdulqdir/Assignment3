package com.example.tae.Assignment3.parking;

import com.example.tae.Assignment3.ui.base.MvpPresenter;

public interface IParkingMvpPresenter <V extends IParkingMvpView> extends MvpPresenter<V> {

    void onViewPrepared();
    void onViewPrepared(int id);
}
