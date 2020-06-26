package com.ttn.rxjava.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ttn.rxjava.R;
import com.ttn.rxjava.utils.MyObserver;
import com.ttn.rxjava.viewmodel.MainViewModel;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FutureAndPublisherOperators extends AppCompatActivity {
    private static final String TAG = "FutureOperator";

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_operator);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        fromFuture();

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

    /*
     * calling future result from viewModel
     */
    private void fromFuture() {
        try {
            viewModel.makeFutureQuery().get()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MyObserver<ResponseBody>());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}