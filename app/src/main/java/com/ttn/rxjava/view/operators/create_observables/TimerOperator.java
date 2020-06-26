package com.ttn.rxjava.view.operators.create_observables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.utils.MyObserver;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TimerOperator extends AppCompatActivity {

    private static final String TAG = "IntervalAndTimer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval_and_timer);



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


}