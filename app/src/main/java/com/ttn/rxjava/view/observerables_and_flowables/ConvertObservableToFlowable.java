package com.ttn.rxjava.view.observerables_and_flowables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.utils.MyFlowableObserver;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConvertObservableToFlowable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_observable_to_flowable);

        convertObservableToFlowable();
    }

    /**
     * Convert Observable to Flowable
     */
    private void convertObservableToFlowable() {
        Observable<Integer> observable = Observable
                .just(1, 2, 3, 4, 5);

//        MISSING, ERROR, BUFFER, DROP, LATEST
        Flowable<Integer> flowable = observable.toFlowable(BackpressureStrategy.BUFFER);
        flowable.onBackpressureBuffer()
                .observeOn(Schedulers.computation())
                .subscribe(new MyFlowableObserver());
    }
}