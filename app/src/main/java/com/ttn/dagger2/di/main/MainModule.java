package com.ttn.dagger2.di.main;

/*
 * Created by Naveen Verma on 11/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import com.ttn.dagger2.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
