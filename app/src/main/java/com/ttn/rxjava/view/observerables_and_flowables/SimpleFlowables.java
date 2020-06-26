package com.ttn.rxjava.view.observerables_and_flowables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ttn.rxjava.R;
import com.ttn.rxjava.utils.MyFlowableObserver;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SimpleFlowables extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_flowables);

        setFlowable().subscribe(new MyFlowableObserver());
    }

    /**
     * Even with 10 million integers, this will not cause an Out of Memory Exception
     */
    private Flowable<Integer> setFlowable() {
        return Flowable.range(0, 100000)
                .onBackpressureBuffer()
                .observeOn(Schedulers.computation());
    }
}