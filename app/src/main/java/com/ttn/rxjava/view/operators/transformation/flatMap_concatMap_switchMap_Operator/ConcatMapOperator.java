package com.ttn.rxjava.view.operators.transformation.flatMap_concatMap_switchMap_Operator;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttn.rxjava.R;
import com.ttn.rxjava.model.Post;
import com.ttn.rxjava.model.service.ServiceGenerator;

import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ConcatMapOperator extends AppCompatActivity {
    private static final String TAG = "ConcatMapOperator";

    //ui
    private RecyclerView recyclerView;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concat_map);

        recyclerView = findViewById(R.id.recycler_view);

        initRecyclerView();

        getPostsObservable()
                .subscribeOn(Schedulers.io())
                /*
                 * Transform the item(s) emitted by an Observable into Observables,
                 * then flatten the emissions from those into a single Observable.
                 * This operator is essentially the same as the Flatmap operator,
                 * but it emits the object(s) while maintaining order.
                 */
                .concatMap((Function<Post, ObservableSource<Post>>) this::getCommentsObservable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {

                    @Override
                    public void onSubscribe(io.reactivex.rxjava3.disposables.@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Post post) {
                        updatePost(post);
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

    /**
     *
     */
    private Observable<Post> getPostsObservable() {
        return ServiceGenerator.getRequestApi()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /*
                 * Transform the item(s) emitted by an Observable into Observables,
                 * then flatten the emissions from those into a single Observable.
                 */
                .flatMap((Function<List<Post>, ObservableSource<Post>>) posts -> {
                    adapter.setPosts(posts);
                    return Observable.fromIterable(posts)
                            .subscribeOn(Schedulers.io());
                });
    }

    private void updatePost(final Post p) {
        Observable
                .fromIterable(adapter.getPosts())
                .filter(post -> post.getId() == p.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {

                    @Override
                    public void onSubscribe(io.reactivex.rxjava3.disposables.@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(TAG, "onNext: updating post: " + post.getId() + ", thread: " + Thread.currentThread().getName());
                        adapter.updatePost(post);
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

    private Observable<Post> getCommentsObservable(final Post post) {
        return ServiceGenerator.getRequestApi()
                .getComments(post.getId())
                .map(comments -> {
                    int delay = ((new Random()).nextInt(5) + 1) * 1000; // sleep thread for x ms
                    Thread.sleep(delay);
                    Log.d(TAG, "apply: sleeping thread " + Thread.currentThread().getName() + " for " + String.valueOf(delay) + "ms");

                    post.setComments(comments);
                    return post;
                })
                .subscribeOn(Schedulers.io());

    }

    private void initRecyclerView() {
        adapter = new RecyclerAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

}