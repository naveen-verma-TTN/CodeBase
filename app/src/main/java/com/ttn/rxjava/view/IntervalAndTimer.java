package com.ttn.rxjava.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.MyObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IntervalAndTimer extends AppCompatActivity {

    private static final String TAG = "IntervalAndTimer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_and_timer);

        observablesUsesIntervalMethod().subscribe(new MyObserver<Long>());

        observablesUsesTimerMethod().subscribe(new MyObserver<Long>());
    }

    /**
     * emit single observable after a given delay
     */
    private Observable<Long> observablesUsesTimerMethod() {
        return Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * emit an observable every time interval
     */
    private Observable<Long> observablesUsesIntervalMethod() {
        return Observable.interval(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .takeWhile(aLong -> {
                    // stop the process if more than 5
//                    Log.d(TAG, "test: " + Thread.currentThread().getName());
                    return aLong <= 5;
                });
    }
}