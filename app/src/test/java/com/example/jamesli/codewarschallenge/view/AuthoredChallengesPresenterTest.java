package com.example.jamesli.codewarschallenge.view;

import com.example.jamesli.codewarschallenge.interactor.GetAuthoredChallengesInteractor;
import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuthoredChallengesPresenterTest {

    private AuthoredChallengesPresenter mSut;

    @Mock
    private GetAuthoredChallengesInteractor mGetAuthoredChallengesInteractor;

    @Mock
    private AuthoredChallengesPresenter.View mView;

    @Mock
    private AuthoredChallengeResponse mAuthoredChallengeResponse;

    @Before
    public void setUp() throws Exception {
        mSut = new AuthoredChallengesPresenter(mGetAuthoredChallengesInteractor);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void updateAuthoredChallenges() throws Exception {
        given(mGetAuthoredChallengesInteractor.getAuthoredChallenges("username")).willReturn(Observable.just(mAuthoredChallengeResponse));
        mSut.startPresenting(mView);

        mSut.getAuthoredChallenges("username");

        verify(mView).handleResponse(mAuthoredChallengeResponse);
    }
}