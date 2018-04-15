package com.example.jamesli.codewarschallenge.di;

import com.example.jamesli.codewarschallenge.view.AuthoredChallengesFragment;
import com.example.jamesli.codewarschallenge.view.ChallengesActivity;
import com.example.jamesli.codewarschallenge.view.CompletedChallengesFragment;
import com.example.jamesli.codewarschallenge.view.MainActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);

    void inject(ChallengesActivity activity);

    void inject(CompletedChallengesFragment fragment);

    void inject(AuthoredChallengesFragment fragment);
}
