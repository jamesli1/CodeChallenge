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
import com.example.jamesli.codewarschallenge.listeners.EndlessRecyclerViewScrollListener;
import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
import com.example.jamesli.codewarschallenge.model.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CompletedChallengesFragment extends Fragment implements CompletedChallengesPresenter.View {

    @BindView(R.id.recycler_view_challenges)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_progress_bar)
    ProgressBar mProgressBar;

    private CompletedChallengesAdapter mAdapter;
    private Unbinder mUnbinder;

    @Inject
    CompletedChallengesPresenter mPresenter;
    public int mTotalItems;
    private int mTotalPages;
    private boolean isLoadingMore = false;
    private Toast mToast;

    public static CompletedChallengesFragment newInstance() {
        return new CompletedChallengesFragment();
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
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new CompletedChallengesAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnSelectedItemChangeListener(completedChallenge -> {
            Intent intent = new Intent(getContext(), ChallengesDetailActivity.class);
            intent.putExtra(Constants.STRING_CHALLENGES_DETAIL, completedChallenge);
            startActivity(intent);
        });
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((LinearLayoutManager) layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadMore(page, totalItemsCount);
            }

            @Override
            public void onReachEnd(int page) {
                if (page == mTotalPages || page == 0) {
                    showNoMoreMessage();
                }
            }
        });
    }

    private void loadMore(int page, int totalItemsCount) {
        if (!isLoadingMore) {
            isLoadingMore = true;
            mAdapter.addNullItem();
        }
        if (page != mTotalPages) {
            mPresenter.getCompletedChallengesByPage(getArguments().getString(Constants.STRING_USERNAME), page);
        }
    }

    private void showNoMoreMessage() {
        String message = getResources().getString(R.string.no_more);
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.startPresenting(this);
        if (mAdapter.getItemCount() == 0) {
            showLoading();
            mPresenter.getCompletedChallenges(getArguments().getString(Constants.STRING_USERNAME));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.stopPresenting();
    }

    @Override
    public void handleResponse(CompletedChallengeResponse completedChallengeResponse) {
        mTotalItems = completedChallengeResponse.getTotalItems();
        mTotalPages = completedChallengeResponse.getTotalPages();
        hideLoading();
        mAdapter.addData(completedChallengeResponse.getCompletedChallenges());
    }

    @Override
    public void handleResponseByPage(CompletedChallengeResponse completedChallengeResponse) {
        isLoadingMore = false;
        mAdapter.removeNullItem();
        mAdapter.addData(completedChallengeResponse.getCompletedChallenges());
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
