package com.ttn.dagger2.ui.auth;

/*
 * Created by Naveen Verma on 10/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.ttn.dagger2.models.User;
import com.ttn.dagger2.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;

    }

    /**
     * authenticate user using id and update the authUser <MediatorLiveDate>
     * @param userId
     */
    public void authenticateWithId(int userId) {
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<User> observeUser(){
        return authUser;
    }
}
