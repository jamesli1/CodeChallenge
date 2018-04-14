package com.example.jamesli.codewarschallenge;

import android.app.Application;

import com.example.jamesli.codewarschallenge.di.ApplicationComponent;
import com.example.jamesli.codewarschallenge.di.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
