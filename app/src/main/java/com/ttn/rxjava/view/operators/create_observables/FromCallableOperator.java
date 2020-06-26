package com.ttn.rxjava.view.operators.create_observables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.MyObserver;

import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FromCallableOperator extends AppCompatActivity {

    // Create observable -- single object
    final Task task = new Task("Walk the dog", false, 3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_callable_operator);

        // --------->method will be executed since now something has subscribed
        createObservablesUsing_fromCallable().subscribe(new MyObserver<Task>());
    }

    /**
     * Used for Room datable or SQL lite database
     */
    private Observable<Task> createObservablesUsing_fromCallable() {
        // --------->create Observable (method will not execute yet)
        return Observable
                .fromCallable(new Callable<Task>() {
                    @Override
                    public Task call() throws Exception {
                        // return database task
//                        return MyDatabase.getTask();
                        return task;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}