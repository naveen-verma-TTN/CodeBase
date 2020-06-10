package com.ttn.dagger2.ui.auth;

/*
 * Created by Naveen Verma on 10/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.ttn.dagger2.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;

        if(this.authApi == null) {
            Log.d(TAG, "AuthViewModel: authApi is NULL");
        }
        else {
            Log.d(TAG, "AuthViewModel: authApi is NOT NULL");
        }
    }
}
