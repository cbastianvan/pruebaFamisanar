package com.dev.Interfaces;


import com.dev.famisanar.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by juan.vanegas on 23/11/2017.
 */

public interface RedditApi {
    @Headers("Content-Type: application/json")
    @GET(".json")
    Call<Feed> getData();
}
