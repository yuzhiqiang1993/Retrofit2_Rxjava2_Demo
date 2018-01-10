package com.esp.yzq.retrofitdemo;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yzq on 2018/1/9.
 */

public interface ApiService {

    @GET("index?type=top&key=4c52313fc9247e5b4176aed5ddd56ad7")
    Call<NewsBean> getNewsCall();


    @GET("index?type=top&key=4c52313fc9247e5b4176aed5ddd56ad7")
    Observable<NewsBean> getNews();


}
