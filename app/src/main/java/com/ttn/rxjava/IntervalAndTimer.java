package com.ttn.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ttn.rxjava.model.Task;

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

    static class MyObserver<T> implements Observer<T> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull T t) {
            if (!t.getClass().getSimpleName().equals("Task")) {
                Log.d(TAG, "onNext: " + t.getClass().getSimpleName() + ": " + t);
            } else {
                Task task = (Task) t;
                Log.d(TAG, "onNext: " + t.getClass().getSimpleName() + ": " + task.getDescription());
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}