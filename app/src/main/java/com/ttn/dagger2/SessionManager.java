package com.ttn.dagger2;

/*
 * Created by Naveen Verma on 11/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.ttn.dagger2.models.User;
import com.ttn.dagger2.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<User>> cacheUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {
        if (cacheUser != null) {
            cacheUser.setValue(AuthResource.loading((User) null));
            cacheUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cacheUser.setValue(userAuthResource);
                    cacheUser.removeSource(source);
                }
            });
        } else {
            Log.d(TAG, "authenticateWithId: previous session detected. Retrieving user from cache.");
        }
    }

    private void logOut() {
        Log.d(TAG, "logOut: logging out...");
        cacheUser.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getAuthUser() {
        return cacheUser;
    }
}
