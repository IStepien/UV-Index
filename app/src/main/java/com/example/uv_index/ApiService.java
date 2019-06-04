package com.example.uv_index;



import com.example.uv_index.model.ResponseResult;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    String apiKey = BuildConfig.ApiKey;

    @Headers("x-access-token: "+apiKey)
    @GET("api/v1/uv?")
    Call<ResponseResult> getCurrentUVData(@Query("lat") String lat, @Query("lng") String lng);
}
