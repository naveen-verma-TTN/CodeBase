package com.ttn.rxjava.utils;

/*
 * Created by Naveen Verma on 26/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.util.Log;

import com.ttn.rxjava.model.Task;

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
    @Override
    public void onNext(@NonNull T t) {
        if (!t.getClass().getSimpleName().equals("Task")) {
            Log.d(TAG, "onNext: " + t.getClass().getSimpleName() + ": " + t);
        } else {
            Task task = (Task) t;
            Log.d(TAG, "onNext: " + t.getClass().getSimpleName() + ": " + task.getDescription());
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