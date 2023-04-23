package com.example.task1;

import android.app.Application;
import com.example.task1.SignalGenerator;

public class App extends Application{

    @Override
    public void onCreate(){
        super.onCreate();

 //       MySP3.init(this);
        SignalGenerator.init(this);
}
}
