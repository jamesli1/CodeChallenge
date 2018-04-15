package com.example.jamesli.codewarschallenge.view;

import com.example.jamesli.codewarschallenge.interactor.GetCompletedChallengesInteractor;
import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CompletedChallengesPresenter {
    private GetCompletedChallengesInteractor mGetCompletedChallengesInteractor;
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private View mView;
    @Inject
    public CompletedChallengesPresenter(GetCompletedChallengesInteractor getCompletedChallengesInteractor) {
        mGetCompletedChallengesInteractor = getCompletedChallengesInteractor;
    }

    public void startPresenting(View view) {
        mView = view;
    }

    public void getCompletedChallenges(String username) {
        Disposable disposable = mGetCompletedChallengesInteractor.getCompletedChallenges(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::handleResponse, mView::handleError);
        mDisposables.add(disposable);
    }

    public void stopPresenting() {
        mDisposables.clear();
    }

    public interface View {
        void handleResponse(CompletedChallengeResponse completedChallengeResponse);

        void handleError(Throwable throwable);
    }
}
