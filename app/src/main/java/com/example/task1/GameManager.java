package com.example.task1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import androidx.core.app.ActivityCompat;


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

    private int carPosition;
    private int score = 0;
    private MediaPlayer mediaPlayer;
    private Context context;

    private double latitude;
    private double longitude;




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
                //SignalGenerator.getInstance().makeCrashSound(MainActivity.getSoundcrash());
                crashSoundStarter();

                SignalGenerator.getInstance().toast("CRUSH!", Toast.LENGTH_SHORT);
                SignalGenerator.getInstance().vibrate(500);

            }
            if ((board[7][i].getVisibility() == VISIBLE) && (coinBoard[6][i].getVisibility() == VISIBLE))
            {
                score += 10;
                scoreBar.setText("" + score);
                //SignalGenerator.getInstance().makeCoindSound(MainActivity.getCoindSound());
                coinSoundStarter();
            }
        }
        score += 1;
        scoreBar.setText(""+ score);
}

    private void gameOver()
    {
        stopTimer();
        Score currentScore = makeLocationForScore();
        if(currentScore.getLatitude() != 0.0 || currentScore.getLongtitude() != 0.0)
        {
            String fromSP =  Sp.getInstance().getString("com.example.task1","");
            ScoreList scorelistFromJson = new Gson().fromJson(fromSP,ScoreList.class );
            if(scorelistFromJson == null)
            {
                scorelistFromJson = new ScoreList();
            }
            scorelistFromJson.addScore(score + "",currentScore.getLatitude(),currentScore.getLongtitude());
            Log.d("From JSON", scorelistFromJson.toString());
            String scoreListJson = new Gson().toJson(scorelistFromJson);
            Log.d("JSON", scoreListJson);
            Sp.getInstance().putString("com.example.task1", scoreListJson);
        }else{
            SignalGenerator.getInstance().toast("Last known location is null", Toast.LENGTH_SHORT);
        }

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
    public void stopTimer(){
        if(timerForGame != null)
            timerForGame.cancel();
        timerForGame = null;
    }

    public void startTime()
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

    private Score makeLocationForScore() {
        Score currScore = new Score();
        currScore.setScore(score + "");

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        Location location = null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (location == null && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location == null && locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }

        if (location != null) {
           latitude = location.getLatitude();
           longitude = location.getLongitude();
        } else {
            latitude = 0.0;
            longitude = 0.0;
        }

        currScore.setLatitude(latitude);
        currScore.setLongtitude(longitude);

        return currScore;


    }

}

