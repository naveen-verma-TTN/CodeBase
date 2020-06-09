package com.ttn.dagger2.di;

import android.app.Application;

import com.ttn.dagger2.BaseApplication;

import dagger.Binds;
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
 */
@Component(
       modules = {
               AndroidSupportInjectionModule.class,
               ActivityBuilderModule.class,
       }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        //bind the instance of the object application class
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
