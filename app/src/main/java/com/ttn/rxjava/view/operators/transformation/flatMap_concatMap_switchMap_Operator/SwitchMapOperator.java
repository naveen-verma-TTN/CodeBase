package com.ttn.rxjava.view.operators.transformation.flatMap_concatMap_switchMap_Operator;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Post;
import com.ttn.rxjava.model.service.ServiceGenerator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * SwitchMap() will transform items emitted by an Observable into an Observable
 * just like ConcatMap() and FlatMap(). The difference being that it will unsubscribe
 * previous observers once a new Observer has subscribed.
 * Essentially this solves a limitation that both ConcatMap() and FlatMap() have.
 */
public class SwitchMapOperator extends AppCompatActivity implements RecyclerAdapter.OnPostClickListener {
    private static final String TAG = "SwitchMapOperator";

    //ui
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private RecyclerAdapter adapter;
    private PublishSubject<Post> publishSubject = PublishSubject.create(); // for selecting a post
    private static final int PERIOD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_map_operator);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);

        initRecyclerView();
        retrievePosts();
    }

    /**
     * Request the Api
     */
    private void retrievePosts() {
        ServiceGenerator.getRequestApi()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        adapter.setPosts(posts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initSwitchMapDemo() {
        publishSubject

                // apply switchMap operator so only one Observable can be used at a time.
                // it clears the previous one
                .switchMap((Function<Post, ObservableSource<Post>>) post -> {
                    // stop the process if more than 5 seconds passes
                    return Observable

                            // simulate slow network speed with interval + takeWhile + filter operators
                            .interval(PERIOD, TimeUnit.MILLISECONDS)
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .takeWhile(aLong -> {
                                Log.d(TAG, "test: " + Thread.currentThread().getName() + ", " + aLong);
                                progressBar.setMax(3000 - PERIOD);
                                progressBar.setProgress(Integer.parseInt(String.valueOf((aLong * PERIOD) + PERIOD)));
                                return aLong <= (3000 / PERIOD);
                            })
                            .filter(aLong -> aLong >= (3000 / PERIOD))

                            // flatMap to convert Long from the interval operator into a Observable<Post>
                            .subscribeOn(Schedulers.io())
                            .flatMap((Function<Long, ObservableSource<Post>>) aLong -> ServiceGenerator.getRequestApi()
                                    .getPost(post.getId()));
                })
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(TAG, "onNext: done.");
                        //navViewPostActivity(post);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setProgress(0);
        initSwitchMapDemo();
    }

    private void initRecyclerView() {
        adapter = new RecyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: called.");
        disposables.clear();
        super.onPause();
    }

    @Override
    public void onPostClick(final int position) {

        Log.d(TAG, "onPostClick: clicked.");

        // submit the selected post object to be queried
        publishSubject.onNext(adapter.getPosts().get(position));
    }
}
