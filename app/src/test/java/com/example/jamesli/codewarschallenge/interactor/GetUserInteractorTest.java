package com.example.jamesli.codewarschallenge.interactor;

import com.example.jamesli.codewarschallenge.model.User;
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
public class GetUserInteractorTest {

    private GetUserInteractor mSut;

    @Mock
    private Repository mRepository;

    @Mock
    private User mUser;

    @Before
    public void setUp() throws Exception {
        mSut = new GetUserInteractor(mRepository);
    }

    @Test
    public void getUserbyName() throws Exception {
        given(mRepository.getUser("username")).willReturn(Observable.just(mUser));

        TestObserver<User> testObserver = mSut.getUserbyName("username").test();

        testObserver.assertSubscribed();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
        testObserver.assertValueCount(1);
        testObserver.assertValue(mUser);
    }
}