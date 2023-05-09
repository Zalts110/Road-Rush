package com.example.task1;

import static com.example.task1.MenuActivity.mLocationPermissionGranted;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
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
         private FusedLocationProviderClient mFusedLocationProviderClient;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.map_fragment, container, false);
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            return view;
}

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getContext());
        try {
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Location currentLocation = (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 15f);
                    }
                }
            });
        } catch (SecurityException e) {
            e.getMessage();
        }
    }

        private void moveCamera(LatLng latLng, float zoom){
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



//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//            mLocationPermissionGranted = false;
//
//            switch (requestCode){
//                case LOCATION_PERMISSION_REQUEST_CODE:{
//                    if(grantResults.length > 0) {
//                        for (int i = 0; i < grantResults.length; i++) {
//                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                                mLocationPermissionGranted = false;
//                                return;
//                            }
//                        }
//                        mLocationPermissionGranted = true;
//                    }
//                }
//            }
//    }
}

