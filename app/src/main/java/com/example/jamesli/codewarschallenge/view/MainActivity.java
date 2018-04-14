package com.example.jamesli.codewarschallenge.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jamesli.codewarschallenge.MyApplication;
import com.example.jamesli.codewarschallenge.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getApplicationComponent().inject(this);
    }
}
