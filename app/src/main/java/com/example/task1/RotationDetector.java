package com.example.task1;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


public class RotationDetector {
    private Sensor sensor;
    private SensorManager sensorManager;
    private rotationCallback rotationCallback;
    private long timestamp = 0;

    private int stepCounterX = 0;
    private int stepCounterY = 0;
    private SensorEventListener sensorLisitner;

    public RotationDetector(Context context, rotationCallback rotationCallback) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.rotationCallback = rotationCallback;
        initEventListener();
    }

    private void initEventListener() {
        sensorLisitner = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                calculateStep(x, y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }


    private void calculateStep(float x, float y) {
        if (System.currentTimeMillis() - timestamp > 500) {
            timestamp = System.currentTimeMillis();
            if (x > 3.0) {
                if (rotationCallback != null)
                    rotationCallback.stepX();
            }
            if (x < -3.0) {
                if (rotationCallback != null)
                    rotationCallback.stepY();
            }
        }
    }


    public void start() {
        sensorManager.registerListener(
                sensorLisitner,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop() {
        sensorManager.unregisterListener(
                sensorLisitner,
                sensor
        );
    }


}
