package com.ttn.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CreateObservables extends AppCompatActivity {
    private static final String TAG = "CreateObservables";

    // Create observable -- single object
    final Task task = new Task("Walk the dog", false, 3);

    // Create observable -- multiple objects
    final List<Task> tasks = DummyDataSource.createTasksList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        createObservablesUsingCreateMethod().subscribe(new MyObserver<Task>());

        createObservablesUsingJustMethod().subscribe(new MyObserver<String>());

        createObservablesUsingRangeMethod().subscribe(new MyObserver<Task>());

        createObservablesUsingRepeatMethod().subscribe(new MyObserver<Integer>());

    }

    /**
     * Create observables using range() and repeat() operators
     */
    private @NonNull Observable<Integer> createObservablesUsingRepeatMethod() {
        return Observable.range(0,2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeat(3);
    }

    /**
     * Create observables using range()
     */
    private @NonNull Observable<Task> createObservablesUsingRangeMethod() {
        return Observable.range(0, 10)
                .subscribeOn(Schedulers.io())
                // map the result to the Observers
                .map(integer -> {
                    Log.d(TAG, "apply: " + Thread.currentThread().getName());
                    return new Task(
                            "this is a task with priority: " + String.valueOf(integer),
                            false,
                            integer);
                })
                // emit result while the task's priority is less than 9
                .takeWhile(task -> task.getPriority() < 9)
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Create observables using just()
     */
    private @NonNull Observable<String> createObservablesUsingJustMethod() {
        return Observable.just("first", "second", "third", "fourth", "fifth", "sixth",
                "seventh", "eighth", "ninth", "tenth")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * fun to create Observables using create()
     */
    private @NonNull Observable<Task> createObservablesUsingCreateMethod() {
        return Observable
                .create((ObservableOnSubscribe<Task>) emitter -> {

                   /* if (!emitter.isDisposed()) {
                        emitter.onNext(task);
                        emitter.onComplete();
                    }*/

                    for (Task task : tasks) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(task);
                        }
                    }
                    if (!emitter.isDisposed()) {
                        emitter.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static class MyObserver<T> implements Observer<T> {

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