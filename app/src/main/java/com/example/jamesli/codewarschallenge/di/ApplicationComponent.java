package com.example.jamesli.codewarschallenge.di;

import com.example.jamesli.codewarschallenge.view.MainActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
