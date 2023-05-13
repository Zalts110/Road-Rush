package com.example.task1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.Random;

public class SensorActivity extends AppCompatActivity {

    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[][] board;
    private AlertDialog.Builder gameOverAlert;

    private GameManager gManger;
    private ShapeableImageView[][] coinBoard;
    private MaterialTextView scoreBar;
    private static final int DEFAULT_SPEED = 1000;
    private RotationDetector rotationDetector;

    Random randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor_main);
        findViews();
        rotationCallback rotationCallback = new rotationCallback() {
            @Override
            public void stepX() {
                gManger.moveLeft(null);
            }

            @Override
            public void stepY() {
                gManger.moveRight(null);
            }
        };
        rotationDetector = new RotationDetector(this.getApplicationContext(),rotationCallback);
        gManger = new GameManager(main_IMG_hearts,board,coinBoard,scoreBar,this.getApplicationContext(),DEFAULT_SPEED);

    }

    private void findViews() {
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.heart1),
                findViewById(R.id.heart2),
                findViewById(R.id.heart3)};

        scoreBar = findViewById(R.id.main_LBL_score);

        board = new ShapeableImageView[][]{
                {findViewById(R.id.sensor_grid_0_0), findViewById(R.id.sensor_grid_0_1), findViewById(R.id.sensor_grid_0_2),findViewById(R.id.sensor_grid_0_3),findViewById(R.id.sensor_grid_0_4)},
                {findViewById(R.id.sensor_grid_1_0), findViewById(R.id.sensor_grid_1_1), findViewById(R.id.sensor_grid_1_2),findViewById(R.id.sensor_grid_1_3),findViewById(R.id.sensor_grid_1_4)},
                {findViewById(R.id.sensor_grid_2_0), findViewById(R.id.sensor_grid_2_1), findViewById(R.id.sensor_grid_2_2),findViewById(R.id.sensor_grid_2_3),findViewById(R.id.sensor_grid_2_4)},
                {findViewById(R.id.sensor_grid_3_0), findViewById(R.id.sensor_grid_3_1), findViewById(R.id.sensor_grid_3_2),findViewById(R.id.sensor_grid_3_3),findViewById(R.id.sensor_grid_3_4)},
                {findViewById(R.id.sensor_grid_4_0), findViewById(R.id.sensor_grid_4_1), findViewById(R.id.sensor_grid_4_2),findViewById(R.id.sensor_grid_4_3),findViewById(R.id.sensor_grid_4_4)},
                {findViewById(R.id.sensor_grid_5_0), findViewById(R.id.sensor_grid_5_1), findViewById(R.id.sensor_grid_5_2),findViewById(R.id.sensor_grid_5_3),findViewById(R.id.sensor_grid_5_4)},
                {findViewById(R.id.sensor_grid_6_0), findViewById(R.id.sensor_grid_6_1), findViewById(R.id.sensor_grid_6_2),findViewById(R.id.sensor_grid_6_3),findViewById(R.id.sensor_grid_6_4)},
                {findViewById(R.id.sensor_grid_7_0), findViewById(R.id.sensor_grid_7_1), findViewById(R.id.sensor_grid_7_2),findViewById(R.id.sensor_grid_7_3),findViewById(R.id.sensor_grid_7_4)},
        };

        coinBoard = new ShapeableImageView[][]{
                {findViewById(R.id.coinSensor_0_0), findViewById(R.id.coinSensor_0_1), findViewById(R.id.coinSensor_0_2),findViewById(R.id.coinSensor_0_3),findViewById(R.id.coinSensor_0_4)},
                {findViewById(R.id.coinSensor_1_0), findViewById(R.id.coinSensor_1_1), findViewById(R.id.coinSensor_1_2),findViewById(R.id.coinSensor_1_3),findViewById(R.id.coinSensor_1_4)},
                {findViewById(R.id.coinSensor_2_0), findViewById(R.id.coinSensor_2_1), findViewById(R.id.coinSensor_2_2),findViewById(R.id.coinSensor_2_3),findViewById(R.id.coinSensor_2_4)},
                {findViewById(R.id.coinSensor_3_0), findViewById(R.id.coinSensor_3_1), findViewById(R.id.coinSensor_3_2),findViewById(R.id.coinSensor_3_3),findViewById(R.id.coinSensor_3_4)},
                {findViewById(R.id.coinSensor_4_0), findViewById(R.id.coinSensor_4_1), findViewById(R.id.coinSensor_4_2),findViewById(R.id.coinSensor_4_3),findViewById(R.id.coinSensor_4_4)},
                {findViewById(R.id.coinSensor_5_0), findViewById(R.id.coinSensor_5_1), findViewById(R.id.coinSensor_5_2),findViewById(R.id.coinSensor_5_3),findViewById(R.id.coinSensor_5_4)},
                {findViewById(R.id.coinSensor_6_0), findViewById(R.id.coinSensor_6_1), findViewById(R.id.coinSensor_6_2),findViewById(R.id.coinSensor_6_3),findViewById(R.id.coinSensor_6_4)},

        };
    }

    @Override
    protected void onPause() {
        gManger.stopTimer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        gManger.startTime();
        if(rotationDetector != null)
            rotationDetector.start();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        gManger.stopTimer();
        rotationDetector.stop();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        gManger.stopTimer();
        super.onStop();
    }

    public void moveRight(View view) {
        gManger.moveRight(view);
    }

    public void moveLeft(View view) {
        gManger.moveLeft(view);
    }


}
