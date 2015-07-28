package com.app.firefighter;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by dolly on 28/7/15.
 */
public class Firefighter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, Constant.KEY_APPLICATION,Constant.KEY_ClIENT);
    }
}
