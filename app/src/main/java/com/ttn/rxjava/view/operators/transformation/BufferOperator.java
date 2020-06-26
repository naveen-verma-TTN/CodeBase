package com.ttn.rxjava.view.operators.transformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BufferOperator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_operator);

        observablesUsesBufferOperator().subscribe(new MyObserver<List<Task>>());
    }

    /**
     * emit the result in group of <2>
     */
    private Observable<List<Task>> observablesUsesBufferOperator() {
        return Observable.fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .buffer(2) // Apply the buffer() operator
                .observeOn(AndroidSchedulers.mainThread());
    }
}