package com.example.tae.Assignment3.parking;

import android.util.Log;

import com.example.tae.Assignment3.data.network.service.DataManager;
import com.example.tae.Assignment3.data.network.model.Parking;
import com.example.tae.Assignment3.ui.base.BasePresenter;
import com.example.tae.Assignment3.ui.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ParkingPresenter<V extends IParkingMvpView>
        extends BasePresenter<V>
        implements IParkingMvpPresenter<V> {


    public ParkingPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    //to get the local parking spots
    @Override
    public void onViewPrepared() {

        getCompositeDisposable()
                .add(getDataManager().getLocations()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Parking>>() {
                            @Override
                            public void accept(List<Parking> parking) throws Exception {
                                getMvpView().onFetchDataCompleted(parking);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.i("error", throwable.getMessage());
                            }
                        }));
    }

    @Override
    public void onViewPrepared(int id) {

        getCompositeDisposable()
                .add(getDataManager().postReservation(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Parking>() {
                            @Override
                            public void accept(Parking parking) throws Exception {
                                getMvpView().onFetchDataCompleted(parking);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().onError(throwable.getMessage());

                            }
                        }));
    }
}