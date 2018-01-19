package com.esp.yzq.retrofitdemo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by yzq on 2018/1/9.
 */

public interface ApiService {


    @GET("index?type=top&key=4c52313fc9247e5b4176aed5ddd56ad7")
    Call<NewsBean> getNewsCall();

    /*动态传参*/
    @GET("index")
    Observable<NewsBean> getNewsWithQuery(@Query("type") String type, @Query("key") String key);

    /*动态传path和参数*/
    @GET("{path}")
    Observable<NewsBean> getNewsWithPathQuery(@Path("path") String path, @Query("type") String type, @Query("key") String key);

    /*通过map传参*/
    @GET("index")
    Observable<NewsBean> getNewsWithQueryMap(@QueryMap Map<String, String> params);

    /*返回的Observable*/
    @GET("index?type=top&key=4c52313fc9247e5b4176aed5ddd56ad7")
    Observable<NewsBean> getNews();


    /*返回的Observable*/
    @POST("index?type=top&key=4c52313fc9247e5b4176aed5ddd56ad7")
    Observable<NewsBean> postNews();


    @FormUrlEncoded
    @POST("index")
    Observable<NewsBean> postNewsWithField(@Field("key") String key, @Field("type") String type);

    @FormUrlEncoded
    @POST("index")
    Observable<NewsBean> postNewsWithFieldMap(@FieldMap Map<String, String> paramsMap);

    @POST("index")
    Observable<NewsBean> postNews(@Body RequestBean requestBean);


}
