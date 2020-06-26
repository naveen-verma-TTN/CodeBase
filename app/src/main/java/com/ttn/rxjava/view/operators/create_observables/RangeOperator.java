package com.ttn.rxjava.view.operators.create_observables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.MyObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RangeOperator extends AppCompatActivity {

    private static final String TAG = "RangeOperator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range_operator);

        createObservablesUsingRangeMethod().subscribe(new MyObserver<Task>());
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
}