package com.ttn.dagger2.di;

import android.app.Application;

import com.ttn.dagger2.BaseApplication;
import com.ttn.dagger2.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/*
  Created by Naveen Verma on 9/6/20.
  Company name: To The New
  Email ID: naveen.verma@tothenew.com
 */

/**
 * Component class for life-time of the application
 * <p>
 * * -----@singleton exist for the entire lifetime of the application
 * <p>
 * AppComponent owns the @Singleton scope
 */

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();

    @Component.Builder
    interface Builder {

        //bind the instance of the object application class
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
