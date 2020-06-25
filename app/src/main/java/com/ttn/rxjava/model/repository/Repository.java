package com.ttn.rxjava.model.repository;

/*
 * Created by Naveen Verma on 25/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.ttn.rxjava.model.service.ServiceGenerator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Repository {

    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    /**
     * Getting flowable result from the Api call and convert it to LiveData
     */
    public LiveData<ResponseBody> makeReactiveQuery() {
        // fromPublisher  -> convert flowable to LiveData
        // toPublisher  -> convert LiveData to flowable
        return LiveDataReactiveStreams.fromPublisher(ServiceGenerator.getRequestApi()
                .makeQuery()
                .subscribeOn(Schedulers.io()));
    }

    /**
     * Getting future result from Api call
     */
    public Future<Observable<ResponseBody>> makeFutureQuery() {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Callable<Observable<ResponseBody>> myNetworkCallable = () -> ServiceGenerator.getRequestApi().makeObservableQuery();

        return new Future<Observable<ResponseBody>>() {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                if (mayInterruptIfRunning) {
                    executor.shutdown();
                }
                return false;
            }

            @Override
            public boolean isCancelled() {
                return executor.isShutdown();
            }

            @Override
            public boolean isDone() {
                return executor.isTerminated();
            }

            @Override
            public Observable<ResponseBody> get() throws ExecutionException, InterruptedException {
                return executor.submit(myNetworkCallable).get();
            }

            @Override
            public Observable<ResponseBody> get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                return executor.submit(myNetworkCallable).get(timeout, unit);
            }
        };

    }

}
