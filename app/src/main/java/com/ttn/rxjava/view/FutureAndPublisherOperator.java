package com.ttn.rxjava.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ttn.rxjava.R;
import com.ttn.rxjava.viewmodel.MainViewModel;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FutureAndPublisherOperator extends AppCompatActivity {
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
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(TAG, "onSubscribe: called.");
                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Log.d(TAG, "onNext: got the response from server!");
                            try {
                                Log.d(TAG, "onNext: " + responseBody.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete: called.");
                        }
                    });
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}