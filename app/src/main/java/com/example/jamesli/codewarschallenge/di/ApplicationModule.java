package com.example.jamesli.codewarschallenge.di;

import com.example.jamesli.codewarschallenge.interactor.GetAuthoredChallengesInteractor;
import com.example.jamesli.codewarschallenge.interactor.GetCompletedChallengesByPageInteractor;
import com.example.jamesli.codewarschallenge.interactor.GetCompletedChallengesInteractor;
import com.example.jamesli.codewarschallenge.interactor.GetUserInteractor;
import com.example.jamesli.codewarschallenge.network.CodewarApiService;
import com.example.jamesli.codewarschallenge.repository.ApiRepository;
import com.example.jamesli.codewarschallenge.repository.Repository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private static final String BASE_URL = "https://www.codewars.com/";

    @Provides
    Retrofit.Builder provideBaseRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    public CodewarApiService providesCodewarApiService(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodewarApiService.class);
    }

    @Provides
    public Repository providesRepository(CodewarApiService codewarApiService) {
        return new ApiRepository(codewarApiService);
    }

    @Provides
    public GetUserInteractor providesGetUserInteractor(Repository repository) {
        return new GetUserInteractor(repository);
    }

    @Provides
    public GetCompletedChallengesInteractor providesGetCompletedChallengesInteractor(Repository repository) {
        return new GetCompletedChallengesInteractor(repository);
    }

    @Provides
    public GetAuthoredChallengesInteractor providesGetAuthoredChallengesInteractor(Repository repository) {
        return new GetAuthoredChallengesInteractor(repository);
    }

    @Provides
    public GetCompletedChallengesByPageInteractor providesGetCompletedChallengesByPageInteractor(Repository repository) {
        return new GetCompletedChallengesByPageInteractor(repository);
    }
}
