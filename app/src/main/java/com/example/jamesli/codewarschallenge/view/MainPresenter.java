package com.example.jamesli.codewarschallenge.view;


import com.example.jamesli.codewarschallenge.interactor.GetUserInteractor;
import com.example.jamesli.codewarschallenge.model.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private GetUserInteractor mGetUserInteractor;
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private View mView;

    @Inject
    public MainPresenter(GetUserInteractor getUserInteractor) {
        mGetUserInteractor = getUserInteractor;
    }

    public void startPresenting(View view) {
        mView = view;
    }

    public void getUser(String username) {
        Disposable disposable = mGetUserInteractor.getUserbyName(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mView::handleResponse, mView::handleError);
        mDisposables.add(disposable);
    }

    public void stopPresenting() {
        mDisposables.clear();
    }

    public interface View {
        void handleResponse(User user);

        void handleError(Throwable throwable);
    }
}
