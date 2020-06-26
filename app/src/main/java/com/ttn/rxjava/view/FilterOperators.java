package com.ttn.rxjava.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilterOperators extends AppCompatActivity {
    private static final String TAG = "FilterOperator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operator);

        filterOperator().subscribe(new MyObserver<Task>());

        distinctOperator().subscribe(new MyObserver<Task>());

        takeOperator().subscribe(new MyObserver<Task>());

        takeWhileOperator().subscribe(new MyObserver<Task>());
    }

    private @NonNull Observable<Task> takeWhileOperator() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .takeWhile(Task::isComplete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    private @NonNull Observable<Task> takeOperator() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .take(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private @NonNull Observable<Task> distinctOperator() {
        return Observable
                .fromIterable(createTasksList())
                .distinct(Task::getDescription)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private @NonNull Observable<Task> filterOperator() {
        return Observable
                .fromIterable(DummyDataSource.createTasksList())
                .filter(task -> {
                    Log.d(TAG, "filterOperator: " + Thread.currentThread().getName());
                    return task.getDescription().equals("Walk the dog");
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Repeated entities
     */
    public static List<Task> createTasksList(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Take out the trash", true, 3));
        tasks.add(new Task("Walk the dog", false, 2));
        tasks.add(new Task("Make my bed", true, 1));
        tasks.add(new Task("Unload the dishwasher", false, 0));
        tasks.add(new Task("Make dinner", true, 5));
        tasks.add(new Task("Make dinner", true, 5)); // duplicate for testing the distinct operator
        return tasks;
    }
}