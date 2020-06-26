package com.ttn.rxjava.view.operators.create_observables;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ttn.rxjava.R;
import com.ttn.rxjava.utils.MyObserver;
import com.ttn.rxjava.viewmodel.MainViewModel;

import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FromFutureOperator extends AppCompatActivity {
    private static final String TAG = "FromFutureOperator";

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_operator);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        fromFuture();
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