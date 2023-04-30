package com.example.task1;

import android.app.Application;
import com.example.task1.SignalGenerator;

public class App extends Application{

    @Override
    public void onCreate(){
        super.onCreate();

        Sp.init(this);
        SignalGenerator.init(this);
}
}
