package com.ttn.rxjava.utils;

/*
 * Created by Naveen Verma on 26/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.ttn.rxjava.model.Task;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MyObserver<T> implements Observer<T> {
    private static final String TAG = "MyObserver";

    // call on subscribing the observable
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.d(TAG, "onSubscribe: called");
    }

    // call on the next iterable item on the observable list (Task list)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onNext(@NonNull T type) {
        if (type.getClass().getSimpleName().equals("Task")) {
            Task task = (Task) type;
            Log.d(TAG, "onNext: " + type.getClass().getSimpleName() + ": " + task.getDescription());

        } else if (type.getClass().getSimpleName().equals("ArrayList")) {
            Log.d(TAG, "onNext: bundle result: ----------------------");
            for (Task task : (List<Task>) type) {
                Log.d(TAG, "onNext: " + type.getClass().getSimpleName() + ": " + task.getDescription());
            }
        } else {
            Log.d(TAG, "onNext: " + type.getClass().getSimpleName() + ": " + type);
        }
    }

    // call on error occurs
    @Override
    public void onError(@NonNull Throwable e) {
        Log.e(TAG, "onError: called", e);
    }

    // call after all the Task are completed
    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: completed..");
    }
}