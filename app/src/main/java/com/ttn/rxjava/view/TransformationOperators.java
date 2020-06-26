package com.ttn.rxjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding3.view.RxView;
import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Task;
import com.ttn.rxjava.utils.DummyDataSource;
import com.ttn.rxjava.utils.MyObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;

public class TransformationOperators extends AppCompatActivity {

    private static final String TAG = "TransformationOperators";

    //ui
    private SearchView searchView;

    private static CompositeDisposable disposables = new CompositeDisposable();

    private static long timeSinceLastRequest; // for log printouts only. Not part of logic.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation_operators);

        observablesUsesMapOperator().subscribe(new MyObserver<String>());

        observablesUsesBufferOperator().subscribe(new MyObserver<List<Task>>());

        rxClickReactiveStream();

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
                .debounce(500,TimeUnit.MILLISECONDS) // Apply Debounce() operator to limit request
                .subscribeOn(io.reactivex.schedulers.Schedulers.io());

    }

    // Fake method for sending a request to the server
    private void sendRequestToServer(String query) {

    }

    /**
     * detect clicks to a button
     * convert the detected clicks to an integer
     */
    private void rxClickReactiveStream() {
        RxView.clicks(findViewById(R.id.button))
                .map((io.reactivex.functions.Function<Unit, Integer>) unit -> 1)
                .buffer(4, TimeUnit.SECONDS) // capture all the clicks during a 4 second interval
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d); // add to disposables to you can clear in onDestroy
                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.d(TAG, "onNext: You clicked " + integers.size() + " times in 4 seconds!");
                    }
                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });


    }

    // make sure to clear disposables when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }


    /**
     * emit the result in group of x - number
     */
    private Observable<List<Task>> observablesUsesBufferOperator() {
        return Observable.fromIterable(DummyDataSource.createTasksList())
                .subscribeOn(Schedulers.io())
                .buffer(2) // Apply the buffer() operator
                .observeOn(AndroidSchedulers.mainThread());
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