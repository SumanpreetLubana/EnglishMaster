package com.binit.buttar.englishmaster.utils;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface API {

    @GET("get-chapter")
    Call<CommonPojo> getchapter(@Header("ApiKey") String accept, @Header("userId") String authorization);

    @GET("get-videos")
    Call<VideoPojo> getvideos(@Header("ApiKey") String accept, @Header("userId") String authorization);

    @GET("get-practice")
    Call<CommonPojo> getpractice(@Header("ApiKey") String accept, @Header("userId") String authorization);

    @GET
    Call<JsonElement> getpracticedetail(@Header("ApiKey") String accept, @Header("userId") String authorization, @Url String url);

    @GET
    Call<CommonPojo> getChapterSubcategories(@Header("ApiKey") String accept, @Header("userId") String authorization, @Url String url);

    @GET
    Call<JsonElement> getChapterSubcategoryDetail(@Header("ApiKey") String accept, @Header("userId") String authorization, @Url String url);

}
