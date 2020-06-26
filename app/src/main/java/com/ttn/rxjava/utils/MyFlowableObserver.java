package com.ttn.rxjava.utils;

/*
 * Created by Naveen Verma on 26/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import android.util.Log;

import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;

/**
 * flowable class
 */
public class MyFlowableObserver
        implements FlowableSubscriber<Integer> /* or we can also use DisposableObserver<Integer>*/ {

    private static final String TAG = "MyFlowableObserver";

    @Override
    public void onSubscribe(@NonNull Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer integer) {
        Log.d(TAG, "onNext: " + integer);
    }

    @Override
    public void onError(Throwable t) {
        Log.e(TAG, "onError: ", t);
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: completed..");
    }


}
