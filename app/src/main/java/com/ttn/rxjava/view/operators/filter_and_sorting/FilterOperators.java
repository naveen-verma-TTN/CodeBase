package com.ttn.rxjava.view.operators.filter_and_sorting;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilterOperators extends AppCompatActivity {
    private static final String TAG = "FilterOperator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operator);

        filterOperator().subscribe(new MyObserver<Task>());






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


}