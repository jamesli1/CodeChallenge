package com.example.jamesli.codewarschallenge.view;

import com.example.jamesli.codewarschallenge.interactor.GetAuthoredChallengesInteractor;
import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthoredChallengesPresenter {
    private GetAuthoredChallengesInteractor mGetAuthoredChallengesInteractor;
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private View mView;

    @Inject
    public AuthoredChallengesPresenter(GetAuthoredChallengesInteractor getAuthoredChallengesInteractor) {
        mGetAuthoredChallengesInteractor = getAuthoredChallengesInteractor;
    }

    public void startPresenting(View view) {
        mView = view;
    }

    public void getAuthoredChallenges(String username) {
        Disposable disposable = mGetAuthoredChallengesInteractor.getAuthoredChallenges(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::handleResponse, mView::handleError);
        mDisposables.add(disposable);
    }

    public void stopPresenting() {
        mDisposables.clear();
    }

    public interface View {
        void handleResponse(AuthoredChallengeResponse authoredChallengeResponse);

        void handleError(Throwable throwable);
    }
}
