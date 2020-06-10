package com.ttn.dagger2.di.auth;

/*
 * Created by Naveen Verma on 10/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import androidx.lifecycle.ViewModel;

import com.ttn.dagger2.di.ViewModelKey;
import com.ttn.dagger2.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
