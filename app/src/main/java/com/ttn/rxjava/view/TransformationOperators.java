package com.ttn.rxjava.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding3.view.RxView;
import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

public class TransformationOperators extends AppCompatActivity {

    private static final String TAG = "TransformationOperators";

    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation_operators);

        observablesUsesMapOperator().subscribe(new MyObserver<String>());

        observablesUsesBufferOperator().subscribe(new MyObserver<List<Task>>());

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


    /**
     * emit the result in group of x - number
     */
    private Observable<List<Task>> observablesUsesBufferOperator() {
        return Observable.fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .buffer(2) // Apply the buffer() operator
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Use of MapOperator to map the Task.getDescription -> String
     */
    private Observable<String> observablesUsesMapOperator() {
        return Observable.fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .map((Function<Task, String>) Task::getDescription)
                .observeOn(AndroidSchedulers.mainThread());
    }
}