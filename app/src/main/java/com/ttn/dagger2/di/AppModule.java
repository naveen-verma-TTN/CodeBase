package com.ttn.dagger2.di;

/*
 * Created by Naveen Verma on 9/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.ttn.dagger2.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application level dependencies
 * -- Retrofit instance, glide instance, etc
 * -----that will not change for the entire lifecycle of the application
 *
 */
@Module
public class AppModule {

    //declare a dependency
    @Singleton
    @Provides
    static RequestOptions provideRequestOption() {
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
}
