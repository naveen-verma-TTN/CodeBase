package com.ttn.rxjava.view.operators.create_observables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.ttn.rxjava.R;
import com.ttn.rxjava.viewmodel.MainViewModel;

import java.io.IOException;

public class FromPublisherOperator extends AppCompatActivity {
    private static final String TAG = "FromPublisherOperator";
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_publisher_operator);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        fromPublisher();
    }

    /*
     * Getting LiveData result from viewModel
     */
    private void fromPublisher() {
        viewModel.makeQuery().observe(this, responseBody -> {
            Log.d(TAG, "onChanged: this is a live data response!");
            try {
                Log.d(TAG, "onChanged: " + responseBody.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}