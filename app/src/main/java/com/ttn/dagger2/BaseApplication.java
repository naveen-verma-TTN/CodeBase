package com.ttn.dagger2;

import com.ttn.dagger2.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/*
  Created by Naveen Verma on 9/6/20.
  Company name: To The New
  Email ID: naveen.verma@tothenew.com
 */

/**
 * Remain for the life-time of the application
 */
public class BaseApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}
