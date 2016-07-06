package com.example.kkiran.weather.Controller;

import android.app.Application;
import android.util.Log;

import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;

/**
 * Created by kkiran on 01/07/16.
 */
public class SetupInstabug {
   public SetupInstabug(){}
    public void initiate(Application application)
    {
        Log.d("%%%%","initiated");
        new Instabug.Builder(application, "4484b84ed248a5dbb0d1c9a22bfa9560")
                .setInvocationEvent(IBGInvocationEvent.IBGInvocationEventShake)
                .build();
    }

}
