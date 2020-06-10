package com.ttn.dagger2.di;

/*
 * Created by Naveen Verma on 9/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import com.ttn.dagger2.di.auth.AuthViewModelModule;
import com.ttn.dagger2.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    // Contribute client
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class
            }
    )
    abstract AuthActivity contributeAuthActivity();

}
