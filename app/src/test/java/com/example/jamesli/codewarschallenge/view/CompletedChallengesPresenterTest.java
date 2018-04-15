package com.example.jamesli.codewarschallenge.view;

import com.example.jamesli.codewarschallenge.interactor.GetCompletedChallengesByPageInteractor;
import com.example.jamesli.codewarschallenge.interactor.GetCompletedChallengesInteractor;
import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;

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
public class CompletedChallengesPresenterTest {

    private CompletedChallengesPresenter mSut;

    @Mock
    private GetCompletedChallengesInteractor mGetCompletedChallengesInteractor;

    @Mock
    private GetCompletedChallengesByPageInteractor mGetCompletedChallengesByPageInteractor;

    @Mock
    private CompletedChallengesPresenter.View mView;

    @Mock
    private CompletedChallengeResponse mCompletedChallengeResponse;

    @Before
    public void setUp() throws Exception {
        mSut = new CompletedChallengesPresenter(mGetCompletedChallengesInteractor, mGetCompletedChallengesByPageInteractor);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void updateCompletedChallenges() throws Exception {
        given(mGetCompletedChallengesInteractor.getCompletedChallenges("username")).willReturn(Observable.just(mCompletedChallengeResponse));
        mSut.startPresenting(mView);

        mSut.getCompletedChallenges("username");

        verify(mView).handleResponse(mCompletedChallengeResponse);
    }

    @Test
    public void updateCompletedChallengesnyPage() throws Exception {
        given(mGetCompletedChallengesByPageInteractor.getCompletedChallengesByPage("username", 1)).willReturn(Observable.just(mCompletedChallengeResponse));
        mSut.startPresenting(mView);

        mSut.getCompletedChallengesByPage("username", 1);

        verify(mView).handleResponseByPage(mCompletedChallengeResponse);
    }
}