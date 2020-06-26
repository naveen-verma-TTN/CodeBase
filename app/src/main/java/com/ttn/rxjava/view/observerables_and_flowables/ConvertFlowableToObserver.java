package com.ttn.rxjava.view.observerables_and_flowables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.utils.MyObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConvertFlowableToObserver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_flowable_to_observer);

        convertFlowableToObserver();
    }

    /**
     * Convert Flowable to Observable
     */
    private void convertFlowableToObserver() {
        Observable<Integer> observable = Observable
                .just(1, 2, 3, 4, 5);

//        MISSING, ERROR, BUFFER, DROP, LATEST
        Flowable<Integer> flowable = observable.toFlowable(BackpressureStrategy.BUFFER);

        Observable<Integer> backToObserver = flowable.toObservable();

        backToObserver.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<Integer>());
    }
}