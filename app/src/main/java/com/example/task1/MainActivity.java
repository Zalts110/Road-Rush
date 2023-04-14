package com.example.task1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ShapeableImageView[] main_IMG_hearts;
    private MaterialButton[] main_BTN_options;
    private ShapeableImageView[][] board;

    private int numOfLanes = 3;
    private CountDownTimer timerForGame;

    private int maxRow = 7;
    private int mistakeCount = 0;
    private int numberOfHearts = 2;

    private static final int DELAY = 500;
    long startTime = 0;

    private boolean randomRock1 = false;

    private AlertDialog.Builder gameOverAlert;



    Random randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameOverAlert = new AlertDialog.Builder(this);
        findViews();
        startTime();
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
        board = new ShapeableImageView[][]{
                {findViewById(R.id.main_grid_0_0), findViewById(R.id.main_grid_0_1), findViewById(R.id.main_grid_0_2)},
                {findViewById(R.id.main_grid_1_0), findViewById(R.id.main_grid_1_1), findViewById(R.id.main_grid_1_2)},
                {findViewById(R.id.main_grid_2_0), findViewById(R.id.main_grid_2_1), findViewById(R.id.main_grid_2_2)},
                {findViewById(R.id.main_grid_3_0), findViewById(R.id.main_grid_3_1), findViewById(R.id.main_grid_3_2)},
                {findViewById(R.id.main_grid_4_0), findViewById(R.id.main_grid_4_1), findViewById(R.id.main_grid_4_2)},
                {findViewById(R.id.main_grid_5_0), findViewById(R.id.main_grid_5_1), findViewById(R.id.main_grid_5_2)},
                {findViewById(R.id.main_grid_6_0), findViewById(R.id.main_grid_6_1), findViewById(R.id.main_grid_6_2)},
                {findViewById(R.id.main_grid_7_0), findViewById(R.id.main_grid_7_1), findViewById(R.id.main_grid_7_2)},
        };
    }



    public void moveRight(View view) {
        if (board[7][0].getVisibility() == VISIBLE) {
            board[7][0].setVisibility(INVISIBLE);
            board[7][1].setVisibility(VISIBLE);


        } else if (board[7][1].getVisibility() == VISIBLE) {
            board[7][1].setVisibility(INVISIBLE);
            board[7][2].setVisibility(VISIBLE);

        }
    }

    public void moveLeft(View view) {
        if (board[7][2].getVisibility() == VISIBLE) {
            board[7][2].setVisibility(INVISIBLE);
            board[7][1].setVisibility(VISIBLE);


        } else if (board[7][1].getVisibility() == VISIBLE) {
            board[7][1].setVisibility(INVISIBLE);
            board[7][0].setVisibility(VISIBLE);

        }
    }

    public void randomRyno()
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(3);
        if(!randomRock1)
        {
            board[0][randomNum].setVisibility(VISIBLE);
            randomRock1 = true;
        }else{
            randomRock1 = false;
        }

    }

    public void moveRynosRowDown() {

        for (int i = maxRow - 1; i > 0; i--)
        {
            for(int j = 0; j < 3; j++)
            {
                board[i][j].setVisibility(board[i - 1][j].getVisibility());
            }
        }

        for(int i = 0; i < 3 ; i ++)
        {
            board[0][i].setVisibility(INVISIBLE);
        }

        lifeDown();
    }

    private void startTime() {
        startTime = System.currentTimeMillis();
        if (timerForGame == null) {
            timerForGame = new CountDownTimer(999999999, DELAY) {
                @Override
                public void onTick(long millisUntilFinished) {
                    moveRynosRowDown();
                    randomRyno();
                }

                @Override
                public void onFinish() {
                    timerForGame.cancel();
                }
            }.start();

        }
    }

    private void lifeDown()
    {
        if (mistakeCount > numberOfHearts)
        {
            gameOver();
        }


        for (int i = 0 ; i < 3; i++)
        {
            if((board[7][i].getVisibility() == VISIBLE) && (board[6][i].getVisibility() == VISIBLE))
            {
                main_IMG_hearts[numberOfHearts - mistakeCount].setVisibility(INVISIBLE);
                mistakeCount++;
                Toast.makeText(this,"Crash!",Toast.LENGTH_LONG).show();
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
            }
        }
    }

private void gameOver()
    {
        stopTimer();
        gameOverAlert.setMessage("press Again for playing another round");
        gameOverAlert.setCancelable(false);

        gameOverAlert.setPositiveButton("AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { initGame();

            }
        });

        AlertDialog message = gameOverAlert.create();
        message.setTitle("You Lost");
        message.show();
    }

    private void initGame()
    {
        for (int i = 0; i < maxRow; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j].setVisibility(INVISIBLE);
            }
        }
        for(int i = 0 ; i < 3; i++)
        {
            main_IMG_hearts[i].setVisibility(VISIBLE);
        }
        mistakeCount = 0;
        timerForGame.start();
    }

    private void stopTimer(){timerForGame.cancel();}
}