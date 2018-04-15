package com.example.jamesli.codewarschallenge.interactor;

import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
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
public class GetCompletedChallengesInteractorTest {

    private GetCompletedChallengesInteractor mSut;

    @Mock
    private Repository mRepository;

    @Mock
    private CompletedChallengeResponse mCompletedChallengeResponse;

    @Before
    public void setUp() throws Exception {
        mSut = new GetCompletedChallengesInteractor(mRepository);
    }

    @Test
    public void getCompletedChallenges() throws Exception {
        given(mRepository.getCompletedChallenges("username")).willReturn(Observable.just(mCompletedChallengeResponse));

        TestObserver<CompletedChallengeResponse> testObserver = mSut.getCompletedChallenges("username").test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(mCompletedChallengeResponse);
    }

}