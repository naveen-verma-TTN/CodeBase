package com.ttn.rxjava.view.operators.create_observables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.utils.MyObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IntervalOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_operator);

        observablesUsesIntervalMethod().subscribe(new MyObserver<Long>());
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