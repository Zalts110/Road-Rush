package com.example.task1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MenuActivity extends AppCompatActivity {
    private AppCompatButton fastModeButton, slowModeButton,leaderBoard;

    private static final String FINE_LOCATION =  android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static boolean mLocationPermissionGranted = false;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        fastModeButton = findViewById(R.id.fastModeButton);
        slowModeButton = findViewById(R.id.slowModeButton);
        leaderBoard = findViewById(R.id.Leaderboard);


        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Leaderboard_Activitiy.class);
                startActivity(intent);

            }
        });

        fastModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gameSpeed = 500;
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("MY_INT_SPEED",gameSpeed);
                startActivity(intent);

            }
        });

        slowModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gameSpeed = 1000;
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("MY_INT_SPEED",gameSpeed);
                startActivity(intent);
            }
        });
        getLocationPermission();
    }

    private void getLocationPermission() {
        String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if((ContextCompat.checkSelfPermission(this,FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) && ContextCompat.checkSelfPermission(this,COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mLocationPermissionGranted = true;
        }
        else{
            ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
}

    }


}

