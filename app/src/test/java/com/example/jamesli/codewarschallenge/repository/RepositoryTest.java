package com.example.jamesli.codewarschallenge.repository;

import com.example.jamesli.codewarschallenge.model.AuthoredChallengeResponse;
import com.example.jamesli.codewarschallenge.model.CompletedChallengeResponse;
import com.example.jamesli.codewarschallenge.model.User;
import com.example.jamesli.codewarschallenge.network.CodewarApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    private Repository mSut;

    @Mock
    private CodewarApiService mCodewarApiService;

    @Mock
    private User mUser;

    @Mock
    private CompletedChallengeResponse mCompletedChallengeResponse;

    @Mock
    private AuthoredChallengeResponse mAuthoredChallengeResponse;

    @Before
    public void setUp() throws Exception {
        mSut = new ApiRepository(mCodewarApiService);
    }

    @Test
    public void getUserFromApi() throws Exception {
        given(mCodewarApiService.getUser("username")).willReturn(Observable.just(mUser));

        TestObserver<User> testObserver = mSut.getUser("username").test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(mUser);
    }

    @Test
    public void getCompletedChallengesApi() throws Exception {
        given(mCodewarApiService.getCompletedChallenges("username")).willReturn(Observable.just(mCompletedChallengeResponse));

        TestObserver<CompletedChallengeResponse> testObserver = mSut.getCompletedChallenges("username").test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(mCompletedChallengeResponse);
    }

    @Test
    public void getAuthoredChallengesFromApi() throws Exception {
        given(mCodewarApiService.getAuthoredChallenges("username")).willReturn(Observable.just(mAuthoredChallengeResponse));

        TestObserver<AuthoredChallengeResponse> testObserver = mSut.getAuthoredChallenges("username").test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(mAuthoredChallengeResponse);
    }

    @Test
    public void getCompletedChallengesByPageFromApi() throws Exception {
        given(mCodewarApiService.getCompletedChallengesByPage("username", 1)).willReturn(Observable.just(mCompletedChallengeResponse));

        TestObserver<CompletedChallengeResponse> testObserver = mSut.getCompletedChallengesByPage("username", 1).test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(mCompletedChallengeResponse);
    }
}