package com.example.jamesli.codewarschallenge.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.jamesli.codewarschallenge.R;
import com.example.jamesli.codewarschallenge.model.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengesActivity extends AppCompatActivity {

    private Bundle bundle;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        ButterKnife.bind(this);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        showCompletedChallenges();
        setTitle(R.string.challenges);
    }

    private void showCompletedChallenges() {
        bundle = new Bundle();
        bundle.putString(Constants.STRING_USERNAME, getIntent().getStringExtra(Constants.STRING_USERNAME));

        CompletedChallengesFragment completedChallengesFragment = CompletedChallengesFragment.newInstance();
        completedChallengesFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.container, completedChallengesFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            (item) -> {
                switch (item.getItemId()) {
                    case R.id.action_completed:
                        bundle = new Bundle();
                        bundle.putString(Constants.STRING_USERNAME, getIntent().getStringExtra(Constants.STRING_USERNAME));

                        CompletedChallengesFragment completedChallengesFragment = CompletedChallengesFragment.newInstance();
                        completedChallengesFragment.setArguments(bundle);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container, completedChallengesFragment)
                                .commit();

                        break;
                    case R.id.action_authored:
                        break;
                }
                return true;
            };
}