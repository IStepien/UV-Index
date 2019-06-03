package com.example.uv_index;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/v1/uv")
    Call<UVDataModel> getCurrentUVData(@Query("lat") String lat, @Query("lon") String lon, @Query("AppID") String app_id);
}
