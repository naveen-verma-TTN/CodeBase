package com.ttn.dagger2.di;

/*
 * Created by Naveen Verma on 9/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import com.ttn.dagger2.di.auth.AuthModule;
import com.ttn.dagger2.di.auth.AuthViewModelModule;
import com.ttn.dagger2.ui.auth.AuthActivity;
import com.ttn.dagger2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    // Contribute client -- to create sub component
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class,
            }
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
