package com.example.jamesli.codewarschallenge.view;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jamesli.codewarschallenge.MyApplication;
import com.example.jamesli.codewarschallenge.R;
import com.example.jamesli.codewarschallenge.error.ErrorHandler;
import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;
import com.example.jamesli.codewarschallenge.model.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AuthoredChallengesFragment extends Fragment implements AuthoredChallengesPresenter.View {

    @BindView(R.id.recycler_view_challenges)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_progress_bar)
    ProgressBar mProgressBar;

    private AuthoredChallengesAdapter mAdapter;
    private Unbinder mUnbinder;

    @Inject
    AuthoredChallengesPresenter mPresenter;

    public static AuthoredChallengesFragment newInstance() {
        return new AuthoredChallengesFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MyApplication) getActivity().getApplication()).getApplicationComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenges, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    private void initViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new AuthoredChallengesAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.startPresenting(this);
        if (mAdapter.getItemCount() == 0) {
            showLoading();
            mPresenter.getAuthoredChallenges(getArguments().getString(Constants.STRING_USERNAME));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stopPresenting();
    }

    @Override
    public void handleResponse(AuthoredChallengeResponse authoredChallengeResponse) {
        hideLoading();
        mAdapter.addData(authoredChallengeResponse.getAuthoredChallenge());
    }

    public void handleError(Throwable throwable) {
        hideLoading();
        String message = getString(ErrorHandler.getMessageFor(throwable));
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }
}
