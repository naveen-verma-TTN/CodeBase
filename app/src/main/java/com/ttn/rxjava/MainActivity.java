package com.ttn.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //ui
    private TextView text;

    //vars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting up the observable and subscribe it
        setObservable().subscribe(new Observer<Task>() {
            /**
             * call on subscribing the observable
             */
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: called");
            }

            /**
             * call on the next iterable item on the observable list (Task list)
             */
            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG, "onNext: " + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + task.getDescription());
            }

            /**
             * call on error occurs
             */
            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: called", e);
            }

            /**
             * call after all the Task are completed
             */
            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: completed..");
            }
        });
    }

    /**
     * fun to set the observable on background Thread
     */
    private Observable<Task> setObservable() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Throwable {
                        Log.d(TAG, "test: " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}