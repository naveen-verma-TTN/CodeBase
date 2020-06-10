package com.ttn.dagger2.di;

/*
 * Created by Naveen Verma on 10/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import androidx.lifecycle.ViewModelProvider;

import com.ttn.dagger2.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Responsible for generating the dependency during the dependency injection
 * for the viewModel factory class
 */

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider
            .Factory bindViewModelFactory
            (ViewModelProviderFactory modelProviderFactory);
}
