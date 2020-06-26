package com.ttn.rxjava.view.operators.transformation;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding3.view.RxView;
import com.ttn.rxjava.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;

public class ThrottleFirstOperator extends AppCompatActivity {

    private static final String TAG = "TransformationOperators";

    //ui
    private Button button;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private long timeSinceLastRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation_operators2);

        button = findViewById(R.id.button);

        timeSinceLastRequest = System.currentTimeMillis();

       throttleFirstOperator().subscribe(new ThrottleFirstObserver());
    }

    /**
     * Avoid multiple request by restricting emission of Observer
     */
    private Observable<Unit> throttleFirstOperator() {
        // Set a click listener to the button with RxBinding Library
        return RxView.clicks(button)
                .throttleFirst(4000, TimeUnit.MILLISECONDS) // Throttle the clicks so 500 ms must pass before registering a new click
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void someMethod() {
        timeSinceLastRequest = System.currentTimeMillis();
        // do something
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // Dispose observable
    }

    private class ThrottleFirstObserver implements Observer<Unit> {
        @Override
        public void onSubscribe(Disposable d) {
            disposables.add(d);
        }

        @Override
        public void onNext(Unit unit) {
            String text = "onNext: time since last clicked: " + (System.currentTimeMillis() - timeSinceLastRequest);
            Log.d(TAG, text);
            Toast.makeText(ThrottleFirstOperator.this, text, Toast.LENGTH_SHORT).show();
            someMethod(); // Execute some method when a click is registered
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    }
}