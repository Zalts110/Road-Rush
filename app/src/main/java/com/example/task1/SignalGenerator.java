package com.example.task1;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

public class SignalGenerator {

    private static SignalGenerator instance = null;
    private Context context;
    private static Vibrator vibrator;
    private static MediaPlayer mediaPlayer;


    private SignalGenerator(Context context) {
        this.context = context;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SignalGenerator(context);
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
    }

    public static SignalGenerator getInstance() {
        return instance;
    }

    public void toast(String text, int length) {
        Toast
                .makeText(context, text, length)
                .show();
    }

    public void vibrate(long length){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(length, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(length);
        }
    }

    public void makeCrashSound(int sound){
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(context,sound);
        if(sound == MainActivity.getSoundcrash())
            mediaPlayer.setVolume(0.7f,0.7f);
        mediaPlayer.start();
    }

    public void makeCoindSound(int sound){
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(context,sound);
        if(sound == MainActivity.getCoindSound())
            mediaPlayer.setVolume(0.7f,0.7f);
        mediaPlayer.start();
    }
}