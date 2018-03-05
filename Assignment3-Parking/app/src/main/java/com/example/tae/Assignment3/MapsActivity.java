package com.example.tae.Assignment3;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tae.Assignment3.data.network.service.DataManager;
import com.example.tae.Assignment3.data.network.model.Parking;
import com.example.tae.Assignment3.parking.AdapterWindow;
import com.example.tae.Assignment3.parking.IParkingMvpView;
import com.example.tae.Assignment3.parking.ParkingPresenter;
import com.example.tae.Assignment3.ui.utils.PermissionUtils;
import com.example.tae.Assignment3.ui.utils.rx.AppSchedulerProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback, IParkingMvpView,
        GoogleMap.OnMyLocationButtonClickListener {


    ParkingPresenter<IParkingMvpView> iParkingMvpPresenter;
   //BitmapDescriptor defaultMarker;
    //private String reserved;
    private GoogleMap mMap;
    //butterKnife
//     @BindView(R.id.mName) TextView mName;
//     @BindView(R.id.mId) TextView mId;
//     @BindView(R.id.mMinLength) TextView mMin;
//     @BindView(R.id.mMaxResLength) TextView mMax;
//     @BindView(R.id.mCostPerMin) TextView mCost;
//     @BindView(R.id.mAvailability) TextView mAvailability;
     //@BindView(R.id.btnSendRes) Button btnSendRes;
     //@BindView(R.id.) EditText mTimeBook;
    TextView mName, mId, mMin, mMax, mCost, mAvailability;
   Button btnSendRes;

    private Context context;
    //MapView mapView;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ButterKnife Binding
        //  ButterKnife.bind(R.layout.info_window, this);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //presenter for the markers
        this.iParkingMvpPresenter = new ParkingPresenter<>(new DataManager(),
                new AppSchedulerProvider(), new CompositeDisposable());
        this.iParkingMvpPresenter.onAttach(this);
        context = this;


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
        //iParkingMvpPresenter.loadParking();
        // getAllParking(); //to get all the parking available
        LatLng temp_ = new LatLng(37.773972, -122.431297);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(temp_, 12), 5000, null);

        iParkingMvpPresenter.onViewPrepared();
       //
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }


    @Override
    public void onFetchDataError(String error) {

    }

    //For sending the request
    @Override
    public void onFetchDataCompleted(Parking parking) {
        Toast.makeText(this, "You have reserved " + parking.getName() + " until " + parking.getIsReserved(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onFetchDataCompleted(List<Parking> parking) {

        mMap.setInfoWindowAdapter(new AdapterWindow(parking));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                iParkingMvpPresenter.onViewPrepared(parking.get(Integer.parseInt(marker.getTitle())).getId());

                iParkingMvpPresenter.onViewPrepared();
            }
        });

       // mMap.clear();
        for (int i = 0; i < parking.size(); i++) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.parseDouble(parking.get(i).getLat()), Double.parseDouble(parking.get(i).getLng())))
                    .title(String.valueOf(i))
                    .icon(BitmapDescriptorFactory.defaultMarker((parking.get(i).getIsReserved()) ? BitmapDescriptorFactory.HUE_RED : BitmapDescriptorFactory.HUE_GREEN)));
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }



}