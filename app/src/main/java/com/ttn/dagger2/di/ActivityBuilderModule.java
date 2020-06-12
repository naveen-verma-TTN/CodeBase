package com.ttn.dagger2.di;

/*
 * Created by Naveen Verma on 9/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import com.ttn.dagger2.di.auth.AuthModule;
import com.ttn.dagger2.di.auth.AuthScope;
import com.ttn.dagger2.di.auth.AuthViewModelModule;
import com.ttn.dagger2.di.main.MainFragmentBuilderModule;
import com.ttn.dagger2.di.main.MainModule;
import com.ttn.dagger2.di.main.MainScope;
import com.ttn.dagger2.di.main.MainViewModelsModule;
import com.ttn.dagger2.ui.auth.AuthActivity;
import com.ttn.dagger2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @AuthScope  // Custom-scope
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class,
            }
    )   // Contribute client -- to create sub component
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuilderModule.class,
                    MainViewModelsModule.class,
                    MainModule.class,
            }
    )
    abstract MainActivity contributeMainActivity();
}
