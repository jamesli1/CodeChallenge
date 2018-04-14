package com.example.jamesli.codewarschallenge.view;

import javax.inject.Inject;


public class MainPresenter {

    private View mView;

    @Inject
    public MainPresenter() {
    }

    public void startPresenting(View view) {
        mView = view;
    }

    public void getUser(String username) {
    }

    public void stopPresenting() {
    }

    public interface View {
    }
}
