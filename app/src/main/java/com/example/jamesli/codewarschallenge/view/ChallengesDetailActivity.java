package com.example.jamesli.codewarschallenge.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jamesli.codewarschallenge.R;
import com.example.jamesli.codewarschallenge.model.AuthoredChallenge;
import com.example.jamesli.codewarschallenge.model.CompletedChallenge;
import com.example.jamesli.codewarschallenge.model.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengesDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_challenge_detail)
    TextView mTextView;

    private String mDetails;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        Parcelable parcelable = getIntent().getExtras().getParcelable(Constants.STRING_CHALLENGES_DETAIL);
        if (parcelable instanceof CompletedChallenge) {
            mDetails = "Id:\n" + ((CompletedChallenge) parcelable).getId() + "\n\nName:\n" + ((CompletedChallenge) parcelable).getName() + "\n\nSlug:\n" + ((CompletedChallenge) parcelable).getSlug() + "\n\nCompleted At:\n" + ((CompletedChallenge) parcelable).getCompletedAt();
            mTitle = ((CompletedChallenge) parcelable).getName();
        } else if (parcelable instanceof AuthoredChallenge) {
            mDetails = "Id:\n" + ((AuthoredChallenge) parcelable).getId() + "\n\nName:\n" + ((AuthoredChallenge) parcelable).getName() + "\n\nRank:\n" + ((AuthoredChallenge) parcelable).getRank() + "\n\nDescription:\n" + ((AuthoredChallenge) parcelable).getDescription();
            mTitle = ((AuthoredChallenge) parcelable).getName();
        }

        setTitle(mTitle);
        mTextView.setText(mDetails);
    }
}