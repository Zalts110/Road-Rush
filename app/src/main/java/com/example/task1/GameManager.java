package com.example.task1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Random;

public class GameManager
{
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[][] board;
    private ShapeableImageView[][] coinBoard;
    private MaterialTextView scoreBar;
    private int numOfLanes = 5;
    private CountDownTimer timerForGame;
    private int maxRow = 7;
    private int mistakeCount = 0;
    private int numberOfHearts = 2;
    private int numOfHearts = 3;
    private int speedDelayChoice;
    long startTime = 0;
    private boolean randomRock1 = false;

//    private AlertDialog.Builder gameOverAlert;
    private int carPosition;
    private int score = 0;
    private MediaPlayer mediaPlayer;
    private Context context;




    public GameManager(ShapeableImageView[] main_IMG_hearts,ShapeableImageView[][] board,ShapeableImageView[][] coinBoard,MaterialTextView scoreBar,Context context,int gameSpeed)
    {
        this.board = board;
        this.main_IMG_hearts = main_IMG_hearts;
        this.mistakeCount = 0;
        this.coinBoard = coinBoard;
        this.scoreBar = scoreBar;
        this.context = context;
        this.speedDelayChoice = gameSpeed;
        carPosition = 2;
        initMediaPlayer(context);
        startTime();

    }

    private void initMediaPlayer(Context context)
    {
        if (mediaPlayer == null)
        {
            mediaPlayer = MediaPlayer.create(context, R.raw.carcrashsound);

        }
    }

    public void releaseMediaPlayer()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
      }
    }

    private void crashSoundStarter()
    {
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(context,R.raw.carcrashsound);
        mediaPlayer.start();
    }

    private void coinSoundStarter()
    {
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(context,R.raw.coinsound);
        mediaPlayer.start();
    }

    public void randomRyno()
    {
        Random col = new Random();
        Random type = new Random();
        int randomNumCoin = type.nextInt(4);
        int randomNum = col.nextInt(5);
        if(!randomRock1)
        {
            if(randomNumCoin == 3)
            {
                coinBoard[0][randomNum].setVisibility(VISIBLE);
            }else
            {
                board[0][randomNum].setVisibility(VISIBLE);
                randomRock1 = true;
            }
        }else{
            randomRock1 = false;
        }

    }
    private void lifeDown()
    {
        if (mistakeCount > numberOfHearts)
        {
           gameOver();


        }


        for (int i = 0 ; i < numOfLanes; i++)
        {
            if((board[7][i].getVisibility() == VISIBLE) && (board[6][i].getVisibility() == VISIBLE))
            {
                main_IMG_hearts[numberOfHearts - mistakeCount].setVisibility(INVISIBLE);
                mistakeCount++;
                crashSoundStarter();

                SignalGenerator.getInstance().toast("CRUSH!", Toast.LENGTH_SHORT);
                SignalGenerator.getInstance().vibrate(500);

            }
            if ((board[7][i].getVisibility() == VISIBLE) && (coinBoard[6][i].getVisibility() == VISIBLE))
            {
                score += 10;
                scoreBar.setText("" + score);
                coinSoundStarter();
            }
        }
        score += 1;
        scoreBar.setText(""+ score);
}

    private void gameOver()
    {
        stopTimer();
        String fromSP =  Sp.getInstance().getString("com.example.task1","");
        ScoreList scorelistFromJson = new Gson().fromJson(fromSP,ScoreList.class );
        if(scorelistFromJson == null)
        {
            scorelistFromJson = new ScoreList();
        }
        scorelistFromJson.addScore(score + "");
        Log.d("From JSON", scorelistFromJson.toString());
        String scoreListJson = new Gson().toJson(scorelistFromJson);
        Log.d("JSON", scoreListJson);
        Sp.getInstance().putString("com.example.task1", scoreListJson);
        returnToMenuActivity();
    }
    private void initGame()
    {
        for (int i = 0; i < maxRow; i++)
        {
            for (int j = 0; j < numOfLanes; j++)
            {
                board[i][j].setVisibility(INVISIBLE);
            }
        }

        for (int i = 0; i < maxRow; i++)
        {
            for (int j = 0; j < numOfLanes; j++)
            {
                coinBoard[i][j].setVisibility(INVISIBLE);
            }
        }

        for(int i = 0 ; i < numOfHearts; i++)
        {
            main_IMG_hearts[i].setVisibility(VISIBLE);
        }
        scoreBar.setText("000");
        mistakeCount = 0;
        score = 0;
        timerForGame.start();
    }
    private void stopTimer(){timerForGame.cancel();}

    private void startTime()
    {
        startTime = System.currentTimeMillis();
        if (timerForGame == null) {
            timerForGame = new CountDownTimer(999999999, speedDelayChoice) {
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
    public void moveRynosRowDown() {

        for (int i = maxRow - 1; i > 0; i--)
        {
            for(int j = 0; j < numOfLanes; j++)
            {
                board[i][j].setVisibility(board[i - 1][j].getVisibility());
                coinBoard[i][j].setVisibility(coinBoard[i - 1][j].getVisibility());
            }
        }

        for(int i = 0; i < numOfLanes ; i ++)
        {
            board[0][i].setVisibility(INVISIBLE);
            coinBoard[0][i].setVisibility(INVISIBLE);
        }

        lifeDown();
    }

    public void moveLeft(View view)
    {
        if(carPosition > 0)
        {
            board[7][carPosition].setVisibility(INVISIBLE);
            board[7][carPosition - 1].setVisibility(VISIBLE);
            carPosition--;
        }
    }

    public void moveRight(View view)
    {
        if(carPosition < numOfLanes - 1)
        {
            board[7][carPosition].setVisibility(INVISIBLE);
            board[7][carPosition + 1].setVisibility(VISIBLE);
            carPosition++;
        }
    }
    public void returnToMenuActivity()
    {
        Intent intent = new Intent(context.getApplicationContext(),MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }



}

