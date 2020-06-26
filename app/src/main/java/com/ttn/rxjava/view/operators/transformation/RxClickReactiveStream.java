package com.ttn.rxjava.view.operators.transformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding3.view.RxView;
import com.ttn.rxjava.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;

public class RxClickReactiveStream extends AppCompatActivity {
    private static final String TAG = "RxClickReactiveStream";

    //ui
    private SearchView searchView;

    private static CompositeDisposable disposables = new CompositeDisposable();

    private static long timeSinceLastRequest; // for log printouts only. Not part of logic.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_click_reactive_stream);

        rxClickReactiveStream();
    }

    /**
     * detect clicks to a button
     * convert the detected clicks to an integer
     */
    private void rxClickReactiveStream() {
        RxView.clicks(findViewById(R.id.button))
                .map((io.reactivex.functions.Function<Unit, Integer>) unit -> 1)
                .buffer(4, TimeUnit.SECONDS) // capture all the clicks during a 4 second interval
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d); // add to disposables to you can clear in onDestroy
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "onNext: You clicked " + integers.size() + " times in 4 seconds!");
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });


    }

    // make sure to clear disposables when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}