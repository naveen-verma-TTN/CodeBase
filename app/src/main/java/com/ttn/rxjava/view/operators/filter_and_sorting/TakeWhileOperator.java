package com.ttn.rxjava.view.operators.filter_and_sorting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TakeWhileOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_while_operator);

        takeWhileOperator().subscribe(new MyObserver<Task>());
    }

    /**
     * Return Observable Task until the isComplete = true
     */
    private @NonNull Observable<Task> takeWhileOperator() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .takeWhile(Task::isComplete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}