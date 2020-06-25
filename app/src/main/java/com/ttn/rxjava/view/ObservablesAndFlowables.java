package com.ttn.rxjava.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;

import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.DisposableContainer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableSubscribeOn;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;

public class ObservablesAndFlowables extends AppCompatActivity {

    // A disposable remove the observer from an observable
    private static CompositeDisposable disposable = new CompositeDisposable();

    private static final String TAG = "ObservablesAndFlowables";

    //ui
    private TextView text;

    //vars


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setObservable().subscribe(new MyObservable());

        setFlowable().subscribe(new MyFlowable());

//        convertObservableToFlowable();

//        convertFlowableToObserver();
    }

    private void convertFlowableToObserver() {
        Observable<Integer> observable = Observable
                .just(1, 2, 3, 4, 5);

//        MISSING, ERROR, BUFFER, DROP, LATEST
        Flowable<Integer> flowable = observable.toFlowable(BackpressureStrategy.BUFFER);

        Observable<Integer> backToObserver = flowable.toObservable();

        backToObserver.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyIntegerObserver());
    }

    private void convertObservableToFlowable() {
        Observable<Integer> observable = Observable
                .just(1, 2, 3, 4, 5);

//        MISSING, ERROR, BUFFER, DROP, LATEST
        Flowable<Integer> flowable = observable.toFlowable(BackpressureStrategy.BUFFER);
        flowable.onBackpressureBuffer()
                .observeOn(Schedulers.computation())
                .subscribe(new MyFlowable());
    }

    /**
     * Even with 10 million integers, this will not cause an Out of Memory Exception
     */
    private Flowable<Integer> setFlowable() {
        return Flowable.range(0, 100000)
                .onBackpressureBuffer()
                .observeOn(Schedulers.computation());
    }

    /**
     * fun to set the observable on background Thread
     */
    private Observable<Task> setObservable() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .filter(task -> {
                    Log.d(TAG, "test: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return task.isComplete();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * flowable class
     */
    static class MyFlowable implements FlowableSubscriber<Integer> {

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

    /**
     * Observer class
     */
    static class MyObservable implements Observer<Task> {
        //          call on subscribing the observable
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            Log.d(TAG, "onSubscribe: called");
            disposable.add(d);
        }

        //           call on the next iterable item on the observable list (Task list)
        @Override
        public void onNext(@NonNull Task task) {
            Log.d(TAG, "onNext: " + Thread.currentThread().getName());
            Log.d(TAG, "onNext: " + task.getDescription());
        }

        //           call on error occurs
        @Override
        public void onError(@NonNull Throwable e) {
            Log.e(TAG, "onError: called", e);
        }

        //           call after all the Task are completed
        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: completed..");
        }
    }

    /**
     * Integer Observer class
     */
    static class MyIntegerObserver implements Observer<Integer> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            Log.d(TAG, "onSubscribe: called");
        }

        @Override
        public void onNext(@NonNull Integer integer) {
            Log.d(TAG, "onNext: " + Thread.currentThread().getName());
            Log.d(TAG, "onNext: " + integer);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.e(TAG, "onError: called", e);
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: completed..");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // It will clear all the subscriber of observers without disabling it -- soft clear
        disposable.clear();
        // It will no longer allow anything to subscribe to observable -- hard clear
        disposable.dispose();
    }
}