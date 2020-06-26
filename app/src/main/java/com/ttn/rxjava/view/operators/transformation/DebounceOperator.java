package com.ttn.rxjava.view.operators.transformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;

import com.ttn.rxjava.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.annotations.NonNull;

public class DebounceOperator extends AppCompatActivity {
    private static final String TAG = "DebounceOperator";

    //ui
    private SearchView searchView;

    private static CompositeDisposable disposables = new CompositeDisposable();

    private static long timeSinceLastRequest; // for log printouts only. Not part of logic.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debounce_operator);

        observablesUsesDebounceOperator().subscribe(new SearchObserver());
    }

    /**
     * Produce delay to the operation
     */
    private io.reactivex.Observable<String> observablesUsesDebounceOperator() {

        searchView = findViewById(R.id.search_view);

        timeSinceLastRequest = System.currentTimeMillis();

        // create the Observable
        return io.reactivex.Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) {

                        // Listen for text input into the searchView
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                if(!emitter.isDisposed()) {
                                    emitter.onNext(newText); // Pass the query to the emitter
                                }
                                return true;
                            }
                        });
                    }
                })
                .debounce(500, TimeUnit.MILLISECONDS) // Apply Debounce() operator to limit request
                .subscribeOn(io.reactivex.schedulers.Schedulers.io());

    }

    // Fake method for sending a request to the server
    private void sendRequestToServer(String query) {

    }

    private class SearchObserver implements Observer<String> {
        @Override
        public void onSubscribe(Disposable d) {
            disposables.add(d);
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext: time  since last request: " + (System.currentTimeMillis() - timeSinceLastRequest));
            Log.d(TAG, "onNext: search query: " + s);
            timeSinceLastRequest = System.currentTimeMillis();

            // method for sending a request to the server
            sendRequestToServer(s);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}