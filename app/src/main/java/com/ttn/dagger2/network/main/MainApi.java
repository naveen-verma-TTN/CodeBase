package com.ttn.dagger2.network.main;

/*
 * Created by Naveen Verma on 11/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

import com.ttn.dagger2.models.Post;

import java.util.List;
import java.util.Queue;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    // posts?userId = 1
    @GET
    Flowable<List<Post>> getPostsFromUser(
            @Query("userId") int id
    );
}
