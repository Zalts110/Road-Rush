package com.example.task1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ShapeableImageView[] main_IMG_hearts;
    private MaterialButton[] main_BTN_options;
    private ShapeableImageView[][] board;

    private int numOfLanes = 5;
    private CountDownTimer timerForGame;

    private int maxRow = 7;
    private int mistakeCount = 0;
    private int numberOfHearts = 2;

    private static final int DELAY = 500;
    long startTime = 0;

    private boolean randomRock1 = false;

    private AlertDialog.Builder gameOverAlert;

    private GameManager gManger;
    private ShapeableImageView[][] coinBoard;

    private MaterialTextView scoreBar;







    Random randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameOverAlert = new AlertDialog.Builder(this);
        findViews();
        gManger = new GameManager(main_IMG_hearts,board,coinBoard,scoreBar,this.getApplicationContext());

    }

    private void findViews() {
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)};
        main_BTN_options = new MaterialButton[]{
                findViewById(R.id.main_FAB_rightbutton),
                findViewById(R.id.main_FAB_leftbutton)
        };
        scoreBar = findViewById(R.id.main_LBL_score);

        board = new ShapeableImageView[][]{
                {findViewById(R.id.main_grid_0_0), findViewById(R.id.main_grid_0_1), findViewById(R.id.main_grid_0_2),findViewById(R.id.main_grid_0_3),findViewById(R.id.main_grid_0_4)},
                {findViewById(R.id.main_grid_1_0), findViewById(R.id.main_grid_1_1), findViewById(R.id.main_grid_1_2),findViewById(R.id.main_grid_1_3),findViewById(R.id.main_grid_1_4)},
                {findViewById(R.id.main_grid_2_0), findViewById(R.id.main_grid_2_1), findViewById(R.id.main_grid_2_2),findViewById(R.id.main_grid_2_3),findViewById(R.id.main_grid_2_4)},
                {findViewById(R.id.main_grid_3_0), findViewById(R.id.main_grid_3_1), findViewById(R.id.main_grid_3_2),findViewById(R.id.main_grid_3_3),findViewById(R.id.main_grid_3_4)},
                {findViewById(R.id.main_grid_4_0), findViewById(R.id.main_grid_4_1), findViewById(R.id.main_grid_4_2),findViewById(R.id.main_grid_4_3),findViewById(R.id.main_grid_4_4)},
                {findViewById(R.id.main_grid_5_0), findViewById(R.id.main_grid_5_1), findViewById(R.id.main_grid_5_2),findViewById(R.id.main_grid_5_3),findViewById(R.id.main_grid_5_4)},
                {findViewById(R.id.main_grid_6_0), findViewById(R.id.main_grid_6_1), findViewById(R.id.main_grid_6_2),findViewById(R.id.main_grid_6_3),findViewById(R.id.main_grid_6_4)},
                {findViewById(R.id.main_grid_7_0), findViewById(R.id.main_grid_7_1), findViewById(R.id.main_grid_7_2),findViewById(R.id.main_grid_7_3),findViewById(R.id.main_grid_7_4)},
        };

        coinBoard = new ShapeableImageView[][]{
                {findViewById(R.id.coin_0_0), findViewById(R.id.coin_0_1), findViewById(R.id.coin_0_2),findViewById(R.id.coin_0_3),findViewById(R.id.coin_0_4)},
                {findViewById(R.id.coin_1_0), findViewById(R.id.coin_1_1), findViewById(R.id.coin_1_2),findViewById(R.id.coin_1_3),findViewById(R.id.coin_1_4)},
                {findViewById(R.id.coin_2_0), findViewById(R.id.coin_2_1), findViewById(R.id.coin_2_2),findViewById(R.id.coin_2_3),findViewById(R.id.coin_2_4)},
                {findViewById(R.id.coin_3_0), findViewById(R.id.coin_3_1), findViewById(R.id.coin_3_2),findViewById(R.id.coin_3_3),findViewById(R.id.coin_3_4)},
                {findViewById(R.id.coin_4_0), findViewById(R.id.coin_4_1), findViewById(R.id.coin_4_2),findViewById(R.id.coin_4_3),findViewById(R.id.coin_4_4)},
                {findViewById(R.id.coin_5_0), findViewById(R.id.coin_5_1), findViewById(R.id.coin_5_2),findViewById(R.id.coin_5_3),findViewById(R.id.coin_5_4)},
                {findViewById(R.id.coin_6_0), findViewById(R.id.coin_6_1), findViewById(R.id.coin_6_2),findViewById(R.id.coin_6_3),findViewById(R.id.coin_6_4)},

        };
    }



    public void moveRight(View view) {
       gManger.moveRight(view);
    }

    public void moveLeft(View view) {
      gManger.moveLeft(view);
    }






    }







