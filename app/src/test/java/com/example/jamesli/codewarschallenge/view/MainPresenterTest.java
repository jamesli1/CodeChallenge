package com.example.jamesli.codewarschallenge.view;

import com.example.jamesli.codewarschallenge.interactor.GetUserInteractor;
import com.example.jamesli.codewarschallenge.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter mSut;

    @Mock
    private GetUserInteractor mGetUserInteractor;

    @Mock
    private MainPresenter.View mView;

    @Mock
    private User mUser;

    @Before
    public void setUp() throws Exception {
        mSut = new MainPresenter(mGetUserInteractor);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void updateUsers() throws Exception {
        given(mGetUserInteractor.getUserbyName("username")).willReturn(Observable.just(mUser));
        mSut.startPresenting(mView);

        mSut.getUser("username");

        verify(mView).handleResponse(mUser);
    }


    @Test
    public void getUserbyNameFailed() throws Exception {
        given(mGetUserInteractor.getUserbyName("username")).willReturn(Observable.error(new Exception()));
        mSut.startPresenting(mView);

        mSut.getUser("username");

        verify(mView).handleError(any(Throwable.class));
    }
}