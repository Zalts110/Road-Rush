package com.example.task1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

public class Leaderboard_Activitiy extends AppCompatActivity
{
    private ListFragment listFragment;
    private MapFragment mapFragment;

    MapCallback mapCallback = new MapCallback() {
        @Override
        public void clickOnRecord(double latitude, double longtitude) {
            Log.d("TAG","firstcheck");
            mapFragment.moveCamera(new LatLng(latitude, longtitude), mapFragment.getDefaultZoom());
            //mapFragment.getLocation();
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_screen);
        initFragments();
        listFragment.setMap_callback(mapCallback);
        beginTransactions();

    }
    private void initFragments() {
        listFragment = new ListFragment();
        mapFragment = new MapFragment();
    }
    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.main_score_list, listFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.main_FRAME_map, mapFragment).commit();
    }


}
