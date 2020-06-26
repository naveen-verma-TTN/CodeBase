package com.ttn.rxjava.view.operators.transformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MapOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation_operators);

        observablesUsesMapOperator().subscribe(new MyObserver<String>());

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