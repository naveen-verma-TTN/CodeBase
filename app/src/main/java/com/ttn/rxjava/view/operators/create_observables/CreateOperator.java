package com.ttn.rxjava.view.operators.create_observables;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CreateOperator extends AppCompatActivity {
    private static final String TAG = "CreateOperator";

    // Create observable -- single object
    final Task task = new Task("Walk the dog", false, 3);

    // Create observable -- multiple objects
    final List<Task> tasks = DummyDataSource.createTasksList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        createObservablesUsingCreateMethod().subscribe(new MyObserver<Task>());
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
}