package com.ttn.rxjava.model.network;

/*
 * Created by Naveen Verma on 25/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface RequestApi {

    @GET("todos/1")
    Observable<ResponseBody> makeObservableQuery();

    @GET("todos/1")
    Flowable<ResponseBody> makeQuery();
}
