package com.example.jamesli.codewarschallenge.interactor;

import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;
import com.example.jamesli.codewarschallenge.repository.Repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GetAuthoredChallengesInteractorTest {

    private GetAuthoredChallengesInteractor mSut;

    @Mock
    private Repository mRepository;

    @Mock
    private AuthoredChallengeResponse mAuthoredChallengeResponse;

    @Before
    public void setUp() throws Exception {
        mSut = new GetAuthoredChallengesInteractor(mRepository);
    }

    @Test
    public void getAuthoredChallenges() throws Exception {
        given(mRepository.getAuthoredChallenges("username")).willReturn(Observable.just(mAuthoredChallengeResponse));

        TestObserver<AuthoredChallengeResponse> testObserver = mSut.getAuthoredChallenges("username").test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(mAuthoredChallengeResponse);
    }
}