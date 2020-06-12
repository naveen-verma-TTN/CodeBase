package com.ttn.dagger2.di.auth;

/*
 * Created by Naveen Verma on 10/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import com.ttn.dagger2.network.auth.AuthApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class AuthModule {

    @AuthScope
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }

    // for same dependency in different scope --- use @Named("xyz")
    @AuthScope
    @Provides
    @Named("auth_dependency")
    static String someString() {
        return "auth_dependency";
    }
}
