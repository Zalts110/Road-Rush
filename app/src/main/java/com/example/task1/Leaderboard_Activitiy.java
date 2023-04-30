package com.example.task1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Leaderboard_Activitiy extends AppCompatActivity
{
    private ListFragment listFragment;
    private MapFragment mapFragment;

    private void showUserLocation(String name) {
        mapFragment.zoomOnUser(name);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard_screen);
        initFragments();
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
