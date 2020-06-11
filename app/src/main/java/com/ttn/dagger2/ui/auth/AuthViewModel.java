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
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;

    }

    /**
     * authenticate user using id and update the authUser <MediatorLiveDate>
     * @param userId
     */
    public void authenticateWithId(int userId) {
        authUser.setValue(AuthResource.loading((User)null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)

                        // instead of calling onError (error happens)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId() == -1) {
                                    return  AuthResource.error("Could not authenticate", (User)null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<AuthResource<User>> observeUser(){
        return authUser;
    }
}
