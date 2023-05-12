package com.example.task1;

import static com.example.task1.MenuActivity.mLocationPermissionGranted;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MapFragment extends Fragment implements OnMapReadyCallback {

        private Fragment mapFragmentView;
        private GoogleMap gMap;
        private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
        private static final String FINE_LOCATION =  android.Manifest.permission.ACCESS_FINE_LOCATION;
        private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
        private static final int ERROR_DIALOG_REQUEST = 9001;
        private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 111;
        private static final float ZOOM = 15f;
         private FusedLocationProviderClient mFusedLocationProviderClient;
         private double latitude;
        private double longtitude;
        private View view;




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.map_fragment, container, false);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            return view;
}

    public void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getContext());
        try {
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Location currentLocation = (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), ZOOM);
                    }
                }
            });
        } catch (SecurityException e) {
            e.getMessage();
        }

    }

        public void moveCamera(LatLng latLng, float zoom){
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
            MarkerOptions options = new MarkerOptions().position(latLng);
            gMap.addMarker(options);
}

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            gMap = googleMap;
            if (mLocationPermissionGranted) {
                getDeviceLocation();
                if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                gMap.setMyLocationEnabled(true);
            }
        }

        public void getLocation(){
            LatLng currentLocation = new LatLng(0, 0);
            gMap.addMarker(new MarkerOptions()
                    .position(currentLocation));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

        }

        public float getDefaultZoom()
        {
            return ZOOM;
        }


    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude){
        this.longtitude = longtitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongtitude(){
        return this.longtitude;
    }


}

